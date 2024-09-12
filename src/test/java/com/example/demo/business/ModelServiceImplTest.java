package com.example.demo.business;

import com.example.demo.business.mappers.ModelMapper;
import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.common.result.Result;
import com.example.demo.dataAccess.BrandRepository;
import com.example.demo.dataAccess.ModelRepository;
import com.example.demo.entities.Brand;
import com.example.demo.entities.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ModelServiceImplTest {
	/*@InjectMocks
	ModelServiceImpl modelServiceImpl;
	@Mock
	private ModelRepository modelRepository;
	@Mock
	BrandRepository brandRepository;
	@Mock
	private ModelMapper modelMapper;

	@Test
	void getAllTest() {
		List<Model> models = new ArrayList<>();
		Mockito.when(modelRepository.findAll()).thenReturn(models);
		Mockito.when(modelMapper.toDtoList(models)).thenReturn(new ArrayList<>());
		Assertions.assertTrue(modelServiceImpl.getAll().isSuccess());
	}

	@Test
	void addTest() {
		CreateModelRequest createModelRequest = new CreateModelRequest();
		Model model = new Model();

		Mockito.when(brandRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Brand()));
		Mockito.when(modelMapper.toEntity(createModelRequest)).thenReturn(model);

		Result result = modelServiceImpl.add(createModelRequest);
		Assertions.assertTrue(result.isSuccess());
		Mockito.verify(modelRepository).save(model);
	}

	@Test
	void addTest_invalidBrandId() {
		CreateModelRequest createModelRequest = new CreateModelRequest();
		Mockito.when(brandRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Result result = modelServiceImpl.add(createModelRequest);
		Assertions.assertFalse(result.isSuccess());
	}*/
}