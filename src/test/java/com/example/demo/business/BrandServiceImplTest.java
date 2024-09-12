package com.example.demo.business;

import com.example.demo.business.mappers.BrandMapper;
import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.common.result.Result;
import com.example.demo.dataAccess.BrandRepository;
import com.example.demo.entities.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
	/*@InjectMocks
	private BrandServiceImpl brandServiceImpl;
	@Mock
	private BrandRepository brandRepository;
	@Mock
	private BrandMapper brandMapper;

	@Test
	void getAllTest() {
		List<Brand> brands = new ArrayList<>();
		Mockito.when(brandRepository.findAll()).thenReturn(brands);
		Mockito.when(brandMapper.toDtoList(brands)).thenReturn(new ArrayList<>());
		Assertions.assertTrue(brandServiceImpl.getAll().isSuccess());
	}

	@Test
	void addTest() {
		CreateBrandRequest brandRequest = new CreateBrandRequest();
		Brand brand = new Brand();
		brand.setName("test");

		Mockito.when(brandMapper.toEntity(brandRequest)).thenReturn(brand);

		Result result = brandServiceImpl.add(brandRequest);

		Mockito.verify(brandRepository).save(Mockito.any(Brand.class));
		Assertions.assertTrue(result.isSuccess());
	}*/
}