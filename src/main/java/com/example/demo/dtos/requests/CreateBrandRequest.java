package com.example.demo.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBrandRequest {
    @Size(min = 2, max = 20, message = "Brand name must be between 2 and 20 characters")
    @NotBlank(message = "Brand name cannot be empty")
    private String name;
}
