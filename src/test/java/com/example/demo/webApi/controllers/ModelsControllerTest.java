package com.example.demo.webApi.controllers;

import com.example.demo.business.ModelService;
import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.business.responses.GetModelResponse;
import com.example.demo.common.result.DataResult;
import com.example.demo.common.result.Result;
import com.example.demo.common.result.SuccessDataResult;
import com.example.demo.common.result.SuccessResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ModelsControllerTest {
	/*@InjectMocks
	private ModelsController modelsController;
	@Mock
	private ModelService modelService;

	@Test
	void getAllTest() {
		DataResult<List<GetModelResponse>> responses = new SuccessDataResult<>(new ArrayList<>());

		Mockito.when(modelService.getAll()).thenReturn(responses);

		Assertions.assertEquals(modelsController.getAll(), ResponseEntity.ok().body(responses));
		Mockito.verify(modelService).getAll();
	}

	@Test
	void addTest() {
		CreateModelRequest request = new CreateModelRequest();
		Result response = new SuccessResult();

		Mockito.when(modelService.add(request)).thenReturn(response);

		Assertions.assertEquals(modelsController.add(request), ResponseEntity.ok().body(response));
		Mockito.verify(modelService).add(request);
	}*/
}