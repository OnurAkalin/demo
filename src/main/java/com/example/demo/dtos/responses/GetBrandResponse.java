package com.example.demo.dtos.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetBrandResponse implements Serializable {
    private int id;
    private String name;
}
