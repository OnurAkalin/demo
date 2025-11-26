package com.example.demo.mappers;

import com.example.demo.dtos.requests.RegisterRequest;
import com.example.demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    User registerRequestToUser(RegisterRequest request);
}

