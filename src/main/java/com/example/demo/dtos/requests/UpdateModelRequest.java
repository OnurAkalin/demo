package com.example.demo.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModelRequest {
    @NotNull(message = "Model id cannot be empty")
    private Long id;

    @NotBlank(message = "Model name cannot be empty")
    @Size(min = 2, max = 20, message = "Model name must be between 2 and 20 characters")
    private String name;
}
