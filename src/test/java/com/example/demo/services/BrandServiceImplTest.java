package com.example.demo.services;

import com.example.demo.dtos.requests.CreateBrandRequest;
import com.example.demo.dtos.requests.UpdateBrandRequest;
import com.example.demo.dtos.responses.GetBrandDetailsResponse;
import com.example.demo.dtos.responses.GetBrandResponse;
import com.example.demo.entities.Brand;
import com.example.demo.mappers.BrandMapper;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    private Brand brand;
    private CreateBrandRequest createBrandRequest;
    private UpdateBrandRequest updateBrandRequest;
    private GetBrandDetailsResponse brandDetailsResponse;
    private GetBrandResponse brandResponse;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(1L);
        brand.setName("Test Brand");

        createBrandRequest = new CreateBrandRequest();
        createBrandRequest.setName("Test Brand");

        updateBrandRequest = new UpdateBrandRequest();
        updateBrandRequest.setId(1L);
        updateBrandRequest.setName("Updated Brand");

        brandDetailsResponse = new GetBrandDetailsResponse();
        brandDetailsResponse.setId(1L);
        brandDetailsResponse.setName("Test Brand");

        brandResponse = new GetBrandResponse();
        brandResponse.setId(1L);
        brandResponse.setName("Test Brand");
    }

    @Test
    void getById_ShouldReturnBrandDetails_WhenBrandExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandMapper.toDetailsDto(brand)).thenReturn(brandDetailsResponse);

        DataResult<GetBrandDetailsResponse> result = brandService.getById(1L);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData()).isNotNull();
        assertThat(result.getData().getId()).isEqualTo(1L);
        assertThat(result.getData().getName()).isEqualTo("Test Brand");

        verify(brandRepository).findById(1L);
        verify(brandMapper).toDetailsDto(brand);
    }

    @Test
    void getById_ShouldReturnError_WhenBrandNotFound() {
        when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());

        DataResult<GetBrandDetailsResponse> result = brandService.getById(999L);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getData()).isNull();

        verify(brandRepository).findById(999L);
        verify(brandMapper, never()).toDetailsDto(any());
    }

    @Test
    void getAll_ShouldReturnBrandList_WhenBrandsExist() {
        List<Brand> brands = Collections.singletonList(brand);
        List<GetBrandResponse> responses = Collections.singletonList(brandResponse);

        when(brandRepository.findAll()).thenReturn(brands);
        when(brandMapper.toDtoList(brands)).thenReturn(responses);

        DataResult<List<GetBrandResponse>> result = brandService.getAll();

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData()).hasSize(1);
        assertThat(result.getData().get(0).getId()).isEqualTo(1L);

        verify(brandRepository).findAll();
        verify(brandMapper).toDtoList(brands);
    }

    @Test
    void add_ShouldCreateBrand_WhenValidRequest() {
        when(brandMapper.toEntity(createBrandRequest)).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);

        Result result = brandService.add(createBrandRequest);

        assertThat(result.isSuccess()).isTrue();

        verify(brandMapper).toEntity(createBrandRequest);
        verify(brandRepository).save(brand);
    }

    @Test
    void update_ShouldUpdateBrand_WhenBrandExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Result result = brandService.update(updateBrandRequest);

        assertThat(result.isSuccess()).isTrue();

        verify(brandRepository).findById(1L);
        verify(brandMapper).toEntity(updateBrandRequest, brand);
        verify(brandRepository).save(brand);
    }

    @Test
    void update_ShouldReturnError_WhenBrandNotFound() {
        when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());

        Result result = brandService.update(updateBrandRequest);

        assertThat(result.isSuccess()).isFalse();

        verify(brandRepository).findById(1L);
        verify(brandMapper, never()).toEntity(any(UpdateBrandRequest.class), any(Brand.class));
        verify(brandRepository, never()).save(any());
    }

    @Test
    void delete_ShouldDeleteBrand_WhenBrandExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        Result result = brandService.delete(1L);

        assertThat(result.isSuccess()).isTrue();

        verify(brandRepository).findById(1L);
        verify(brandRepository).delete(brand);
    }

    @Test
    void delete_ShouldReturnError_WhenBrandNotFound() {
        when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());

        Result result = brandService.delete(999L);

        assertThat(result.isSuccess()).isFalse();

        verify(brandRepository).findById(999L);
        verify(brandRepository, never()).delete(any());
    }
}