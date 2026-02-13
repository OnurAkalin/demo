package com.example.demo.service.impl;

import com.example.demo.constant.AppConstants;
import com.example.demo.constant.CacheConstants;
import com.example.demo.constant.Status;
import com.example.demo.constant.UIMessages;
import com.example.demo.dto.request.CreateModelRequest;
import com.example.demo.dto.request.UpdateModelRequest;
import com.example.demo.dto.response.GetModelDetailsResponse;
import com.example.demo.dto.response.GetModelResponse;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Model;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ModelRepository;
import com.example.demo.service.ModelService;
import com.example.demo.util.result.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = CacheConstants.MODELS, key = "#id")
    @Override
    public DataResult<GetModelDetailsResponse> getById(Long id) {
        Model model = modelRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (model == null) {
            return new ErrorDataResult<>(null, UIMessages.NOT_FOUND_DATA);
        }

        GetModelDetailsResponse response = modelMapper.toDetailsDto(model);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = CacheConstants.MODELS, key = CacheConstants.ALL_KEY)
    @Override
    public DataResult<List<GetModelResponse>> getAll() {
        List<Model> models = modelRepository.findAllByStatus(Status.ACTIVE);

        List<GetModelResponse> response = modelMapper.toDtoList(models);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Override
    public DataResult<PagedResponse<GetModelResponse>> getAllPaged(int pageNo) {
        PageRequest pageRequest = PageRequest.of(Math.max(pageNo - 1, 0), AppConstants.MODELS_PAGE_SIZE);

        Page<Model> models = modelRepository.findAllByStatus(Status.ACTIVE, pageRequest);

        List<GetModelResponse> content = modelMapper.toDtoList(models.getContent());

        PagedResponse<GetModelResponse> response = new PagedResponse<>(content, models.getNumber() + 1, models.getSize(), models.getTotalPages());

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public Result add(CreateModelRequest createModelRequest) {
        Brand brand = brandRepository.findByIdAndStatus(createModelRequest.getBrandId(), Status.ACTIVE);
        if (brand == null) {
            return new ErrorResult(UIMessages.ERROR);
        }

        Model model = modelMapper.toEntity(createModelRequest);
        modelRepository.save(model);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public Result update(UpdateModelRequest updateModelRequest) {
        Model model = modelRepository.findByIdAndStatus(updateModelRequest.getId(), Status.ACTIVE);
        if (model == null) {
            return new ErrorResult(UIMessages.NOT_FOUND_DATA);
        }

        modelMapper.toEntity(updateModelRequest, model);
        modelRepository.save(model);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public Result delete(Long id) {
        Model model = modelRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (model == null) {
            return new ErrorResult(UIMessages.NOT_FOUND_DATA);
        }

        model.setStatus(Status.DELETED);
        modelRepository.save(model);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public void hardDeleteAll() {
        modelRepository.deleteAll();
    }
}
