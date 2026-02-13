package com.example.demo.mapper;

import com.example.demo.dto.request.CreateModelRequest;
import com.example.demo.dto.request.UpdateModelRequest;
import com.example.demo.dto.response.GetModelDetailsResponse;
import com.example.demo.dto.response.GetModelResponse;
import com.example.demo.entity.Model;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = BrandMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ModelMapper {
    @Mapping(source = "brandId", target = "brand.id")
    Model toEntity(CreateModelRequest createModelRequest);

    GetModelDetailsResponse toDetailsDto(Model model);

    @Mapping(source = "brand.id", target = "brandId")
    GetModelResponse toDto(Model model);

    List<GetModelResponse> toDtoList(List<Model> models);

    void toEntity(UpdateModelRequest updateModelRequest, @MappingTarget Model model);
}
