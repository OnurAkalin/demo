package com.example.demo.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {
    @NotBlank(message = "Brand name cannot be empty")
    @Size(min = 2, max = 50, message = "Brand name must be between 2 and 50 characters")
    private String name;
}
