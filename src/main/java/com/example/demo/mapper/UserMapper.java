package com.example.demo.mapper;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    User registerRequestToUser(RegisterRequest request);
}

