package com.example.demo.mapper;

import com.example.demo.dto.request.CreateBrandRequest;
import com.example.demo.dto.request.UpdateBrandRequest;
import com.example.demo.dto.response.GetBrandDetailsResponse;
import com.example.demo.dto.response.GetBrandResponse;
import com.example.demo.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = ModelMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BrandMapper {
    Brand toEntity(CreateBrandRequest createBrandRequest);

    GetBrandDetailsResponse toDetailsDto(Brand brand);

    GetBrandResponse toDto(Brand brand);

    List<GetBrandResponse> toDtoList(List<Brand> brands);

    void toEntity(UpdateBrandRequest updateBrandRequest, @MappingTarget Brand brand);
}
