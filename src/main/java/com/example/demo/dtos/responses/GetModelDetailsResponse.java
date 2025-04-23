package com.example.demo.dtos.responses;

import lombok.Data;

@Data
public class GetModelDetailsResponse {
    private Long id;
    private String name;
    private GetBrandResponse brand;
}
