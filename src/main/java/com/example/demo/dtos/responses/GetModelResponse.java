package com.example.demo.dtos.responses;

import lombok.Data;

@Data
public class GetModelResponse {
    private Long id;
    private String name;
    private Long brandId;
}
