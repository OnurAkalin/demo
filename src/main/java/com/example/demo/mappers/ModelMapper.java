package com.example.demo.mappers;

import com.example.demo.dtos.requests.CreateModelRequest;
import com.example.demo.dtos.requests.UpdateModelRequest;
import com.example.demo.dtos.responses.GetModelDetailsResponse;
import com.example.demo.dtos.responses.GetModelResponse;
import com.example.demo.entities.Model;
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
