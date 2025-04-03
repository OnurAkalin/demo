package com.example.demo.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetBrandDetailsResponse {
    private Long id;
    private String name;
    List<GetModelResponse> models;
}
