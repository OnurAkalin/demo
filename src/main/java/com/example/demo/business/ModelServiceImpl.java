package com.example.demo.business;

import com.example.demo.business.mappers.ModelMapper;
import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.business.requests.UpdateModelRequest;
import com.example.demo.business.responses.GetModelDetailsResponse;
import com.example.demo.business.responses.GetModelResponse;
import com.example.demo.common.constants.CacheConstants;
import com.example.demo.common.constants.UIMessages;
import com.example.demo.common.result.*;
import com.example.demo.dataAccess.BrandRepository;
import com.example.demo.dataAccess.ModelRepository;
import com.example.demo.entities.Brand;
import com.example.demo.entities.Model;
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
public class ModelServiceImpl implements ModelService {
	private final ModelRepository modelRepository;
	private final BrandRepository brandRepository;
	private final ModelMapper mapper;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Cacheable(value = CacheConstants.MODELS, key = "#id")
	@Override
	public DataResult<GetModelDetailsResponse> getById(int id) {
		Model model = modelRepository.findById(id).orElse(null);
		if (model == null) {
			return new ErrorDataResult<>(null, UIMessages.NOT_FOUND_DATA);
		}

		GetModelDetailsResponse response = mapper.toDetailsDto(model);

		return new SuccessDataResult<>(response, UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Cacheable(value = CacheConstants.MODELS, key = CacheConstants.ALL_KEY)
	@Override
	public DataResult<List<GetModelResponse>> getAll() {
		List<GetModelResponse> response;
		List<Model> models = modelRepository.findAll();
		response = mapper.toDtoList(models);

		return new SuccessDataResult<>(response, UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
	@Override
	public Result add(CreateModelRequest createModelRequest) {
		Brand brand = brandRepository.findById(createModelRequest.getBrandId()).orElse(null);
		if (brand == null) {
			return new ErrorResult(UIMessages.ERROR);
		}

		Model model = mapper.toEntity(createModelRequest);
		model.setBrand(brand);
		modelRepository.save(model);

		return new SuccessResult(UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
	@Override
	public Result update(UpdateModelRequest updateModelRequest) {
		Model model = modelRepository.findById(updateModelRequest.getId()).orElse(null);
		if (model == null) {
			return new ErrorResult(UIMessages.NOT_FOUND_DATA);
		}

		Brand brand = brandRepository.findById(updateModelRequest.getBrandId()).orElse(null);
		if (brand == null) {
			return new ErrorResult(UIMessages.ERROR);
		}

		mapper.toEntity(updateModelRequest, model);
		model.setBrand(brand);
		modelRepository.save(model);

		return new SuccessResult(UIMessages.SUCCESS);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CacheEvict(value = {CacheConstants.BRANDS, CacheConstants.MODELS}, allEntries = true)
	@Override
	public Result delete(int id) {
		Model model = modelRepository.findById(id).orElse(null);
		if (model == null) {
			return new ErrorResult(UIMessages.NOT_FOUND_DATA);
		}

		modelRepository.delete(model);

		return new SuccessResult(UIMessages.SUCCESS);
	}
}
