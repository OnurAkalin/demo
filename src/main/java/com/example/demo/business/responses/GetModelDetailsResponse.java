package com.example.demo.business.responses;

import lombok.Data;

@Data
public class GetModelDetailsResponse {
	private int id;
	private String name;
	private GetBrandResponse brand;
}
