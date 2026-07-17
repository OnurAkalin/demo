package com.example.demo.service.impl;

import com.example.demo.constant.CacheConstants;
import com.example.demo.constant.Status;
import com.example.demo.constant.UIMessages;
import com.example.demo.dto.request.CreateBrandRequest;
import com.example.demo.dto.request.UpdateBrandRequest;
import com.example.demo.dto.response.GetBrandDetailsResponse;
import com.example.demo.dto.response.GetBrandResponse;
import com.example.demo.entity.Brand;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.BrandMapper;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;
import com.example.demo.util.result.SuccessDataResult;
import com.example.demo.util.result.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Cacheable(value = CacheConstants.BRANDS, key = "#id")
    @Transactional(readOnly = true)
    @Override
    public DataResult<GetBrandDetailsResponse> getById(Long id) {
        Brand brand = brandRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (brand == null) {
            throw new NotFoundException();
        }

        GetBrandDetailsResponse response = brandMapper.toDetailsDto(brand);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Cacheable(value = CacheConstants.BRANDS, key = CacheConstants.ALL_KEY)
    @Transactional(readOnly = true)
    @Override
    public DataResult<List<GetBrandResponse>> getAll() {
        List<Brand> brands = brandRepository.findAllByStatus(Status.ACTIVE);

        List<GetBrandResponse> response = brandMapper.toDtoList(brands);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Transactional
    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        Brand brand = brandMapper.toEntity(createBrandRequest);

        brandRepository.save(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Transactional
    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = brandRepository.findByIdAndStatus(updateBrandRequest.getId(), Status.ACTIVE);
        if (brand == null) {
            throw new NotFoundException();
        }

        brandMapper.toEntity(updateBrandRequest, brand);
        brandRepository.save(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Transactional
    @Override
    public Result delete(Long id) {
        Brand brand = brandRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (brand == null) {
            throw new NotFoundException();
        }

        brand.setStatus(Status.DELETED);
        brandRepository.softDeleteModelsByBrandId(id);
        brandRepository.save(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Transactional
    @Override
    public void hardDeleteAll() {
        brandRepository.deleteAll();
    }
}
