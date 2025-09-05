package com.example.demo.services;

import com.example.demo.constants.AppConstants;
import com.example.demo.constants.CacheConstants;
import com.example.demo.constants.UIMessages;
import com.example.demo.dtos.requests.CreateModelRequest;
import com.example.demo.dtos.requests.UpdateModelRequest;
import com.example.demo.dtos.responses.GetModelDetailsResponse;
import com.example.demo.dtos.responses.GetModelResponse;
import com.example.demo.dtos.responses.PagedResponse;
import com.example.demo.entities.Brand;
import com.example.demo.entities.Model;
import com.example.demo.mappers.ModelMapper;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.utils.result.*;
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

    @Cacheable(value = CacheConstants.MODELS, key = "#id")
    @Override
    public DataResult<GetModelDetailsResponse> getById(Long id) {
        Model model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            return new ErrorDataResult<>(null, UIMessages.NOT_FOUND_DATA);
        }

        GetModelDetailsResponse response = modelMapper.toDetailsDto(model);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Cacheable(value = CacheConstants.MODELS, key = CacheConstants.ALL_KEY)
    @Override
    public DataResult<List<GetModelResponse>> getAll() {
        List<Model> models = modelRepository.findAll();

        List<GetModelResponse> response = modelMapper.toDtoList(models);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Override
    public DataResult<PagedResponse<GetModelResponse>> getAllPaged(int pageNo) {
        PageRequest pageRequest = PageRequest.of(Math.max(pageNo - 1, 0), AppConstants.MODELS_PAGE_SIZE);

        Page<Model> models = modelRepository.findAll(pageRequest);

        List<GetModelResponse> content = modelMapper.toDtoList(models.getContent());

        PagedResponse<GetModelResponse> response = new PagedResponse<>(content, models.getNumber() + 1, models.getSize(), models.getTotalPages());

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public Result add(CreateModelRequest createModelRequest) {
        Brand brand = brandRepository.findById(createModelRequest.getBrandId()).orElse(null);
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
        Model model = modelRepository.findById(updateModelRequest.getId()).orElse(null);
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
        Model model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            return new ErrorResult(UIMessages.NOT_FOUND_DATA);
        }

        modelRepository.delete(model);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public void deleteAll() {
        modelRepository.deleteAll();
    }
}
