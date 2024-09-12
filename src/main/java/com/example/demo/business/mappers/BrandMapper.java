package com.example.demo.business.mappers;

import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.business.requests.UpdateBrandRequest;
import com.example.demo.business.responses.GetBrandDetailsResponse;
import com.example.demo.business.responses.GetBrandResponse;
import com.example.demo.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = ModelMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {
	Brand toEntity(CreateBrandRequest createBrandRequest);

	GetBrandDetailsResponse toDetailsDto(Brand brand);

	GetBrandResponse toDto(Brand brand);

	List<GetBrandResponse> toDtoList(List<Brand> brands);

	void toEntity(UpdateBrandRequest updateBrandRequest, @MappingTarget Brand brand);
}
