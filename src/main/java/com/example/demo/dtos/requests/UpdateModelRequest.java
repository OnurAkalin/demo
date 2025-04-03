package com.example.demo.dtos.requests;

import lombok.Data;

@Data
public class UpdateModelRequest {
    private Long id;
    private String name;
    private Long brandId;
}
