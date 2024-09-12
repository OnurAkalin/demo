package com.example.demo.business;

import com.example.demo.business.mappers.BrandMapper;
import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.business.requests.UpdateBrandRequest;
import com.example.demo.business.responses.GetBrandDetailsResponse;
import com.example.demo.business.responses.GetBrandResponse;
import com.example.demo.common.constants.CacheConstants;
import com.example.demo.common.constants.UIMessages;
import com.example.demo.common.result.*;
import com.example.demo.dataAccess.BrandRepository;
import com.example.demo.entities.Brand;
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
public class BrandServiceImpl implements BrandService {
	private final BrandRepository brandRepository;
	private final BrandMapper mapper;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Cacheable(value = CacheConstants.BRANDS, key = "#id")
	@Override
	public DataResult<GetBrandDetailsResponse> getById(int id) {
		Brand brand = brandRepository.findById(id).orElse(null);
		if (brand == null) {
			return new ErrorDataResult<>(null, UIMessages.NOT_FOUND_DATA);
		}

		GetBrandDetailsResponse response = mapper.toDetailsDto(brand);

		return new SuccessDataResult<>(response, UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Cacheable(value = CacheConstants.BRANDS, key = CacheConstants.ALL_KEY)
	@Override
	public DataResult<List<GetBrandResponse>> getAll() {
		List<GetBrandResponse> response;
		var brands = brandRepository.findAll();
		response = mapper.toDtoList(brands);

		return new SuccessDataResult<>(response, UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Brand brand = mapper.toEntity(createBrandRequest);
		brandRepository.save(brand);

		return new SuccessResult(UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CacheEvict(value = CacheConstants.BRANDS, allEntries = true)
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = brandRepository.findById(updateBrandRequest.getId()).orElse(null);
		if (brand == null) {
			return new ErrorResult(UIMessages.NOT_FOUND_DATA);
		}

		mapper.toEntity(updateBrandRequest, brand);
		brandRepository.save(brand);
		brandRepository.updateRelatedTables(brand.getId());

		return new SuccessResult(UIMessages.SUCCESS);
	}
}
