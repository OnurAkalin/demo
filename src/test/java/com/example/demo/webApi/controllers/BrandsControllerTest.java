package com.example.demo.webApi.controllers;

import com.example.demo.business.BrandService;
import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.business.responses.GetBrandResponse;
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
class BrandsControllerTest {
	/*@InjectMocks
	private BrandsController brandsController;
	@Mock
	private BrandService brandService;

	@Test
	void getAllTest() {
		DataResult<List<GetBrandResponse>> response = new SuccessDataResult<>(new ArrayList<>());

		Mockito.when(brandService.getAll()).thenReturn(response);

		Assertions.assertEquals(brandsController.getAll(), ResponseEntity.ok(response));
		Mockito.verify(brandService).getAll();
	}

	@Test
	void addTest() {
		CreateBrandRequest request = new CreateBrandRequest();
		Result response = new SuccessResult();

		Mockito.when(brandService.add(request)).thenReturn(response);

		Assertions.assertEquals(brandsController.add(request), ResponseEntity.ok(response));
		Mockito.verify(brandService).add(request);
	}*/
}