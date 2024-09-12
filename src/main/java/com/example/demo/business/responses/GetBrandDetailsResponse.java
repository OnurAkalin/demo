package com.example.demo.business.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetBrandDetailsResponse {
	private int id;
	private String name;
	List<GetModelResponse> models;
}
