package com.example.demo.business.mappers;

import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.business.requests.UpdateModelRequest;
import com.example.demo.business.responses.GetModelDetailsResponse;
import com.example.demo.business.responses.GetModelResponse;
import com.example.demo.entities.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = BrandMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {
	@Mapping(source = "brandId", target = "brand.id")
	Model toEntity(CreateModelRequest createModelRequest);

	GetModelDetailsResponse toDetailsDto(Model model);

	GetModelResponse toDto(Model model);

	List<GetModelResponse> toDtoList(List<Model> models);

	void toEntity(UpdateModelRequest updateModelRequest, @MappingTarget Model model);
}
