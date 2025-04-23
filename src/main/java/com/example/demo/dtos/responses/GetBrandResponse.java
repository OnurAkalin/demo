package com.example.demo.dtos.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetBrandResponse implements Serializable {
    private Long id;
    private String name;
}
