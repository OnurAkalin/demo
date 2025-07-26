package com.example.demo.services;

import com.example.demo.constants.CacheConstants;
import com.example.demo.constants.UIMessages;
import com.example.demo.dtos.requests.CreateBrandRequest;
import com.example.demo.dtos.requests.UpdateBrandRequest;
import com.example.demo.dtos.responses.GetBrandDetailsResponse;
import com.example.demo.dtos.responses.GetBrandResponse;
import com.example.demo.entities.Brand;
import com.example.demo.mappers.BrandMapper;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.utils.result.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Cacheable(value = CacheConstants.BRANDS, key = "#id")
    @Override
    public DataResult<GetBrandDetailsResponse> getById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            return new ErrorDataResult<>(null, UIMessages.NOT_FOUND_DATA);
        }

        GetBrandDetailsResponse response = brandMapper.toDetailsDto(brand);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @Cacheable(value = CacheConstants.BRANDS, key = CacheConstants.ALL_KEY)
    @Override
    public DataResult<List<GetBrandResponse>> getAll() {
        List<Brand> brands = brandRepository.findAll();

        List<GetBrandResponse> response = brandMapper.toDtoList(brands);

        return new SuccessDataResult<>(response, UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        Brand brand = brandMapper.toEntity(createBrandRequest);

        brandRepository.save(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = brandRepository.findById(updateBrandRequest.getId()).orElse(null);
        if (brand == null) {
            return new ErrorResult(UIMessages.NOT_FOUND_DATA);
        }

        brandMapper.toEntity(updateBrandRequest, brand);
        brandRepository.save(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
    @Override
    public Result delete(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            return new ErrorResult(UIMessages.NOT_FOUND_DATA);
        }

        brandRepository.delete(brand);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
    @Override
    public void deleteAll() {
        brandRepository.deleteAll();
    }
}
