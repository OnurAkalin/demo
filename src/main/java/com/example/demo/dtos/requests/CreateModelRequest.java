package com.example.demo.dtos.requests;

import lombok.Data;

@Data
public class CreateModelRequest {
    private String name;
    private int brandId;
}
