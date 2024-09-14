package com.example.demo.business;

import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.business.requests.UpdateBrandRequest;
import com.example.demo.business.responses.GetBrandDetailsResponse;
import com.example.demo.business.responses.GetBrandResponse;
import com.example.demo.common.result.DataResult;
import com.example.demo.common.result.Result;

import java.util.List;

public interface BrandService {
	DataResult<GetBrandDetailsResponse> getById(int id);

	DataResult<List<GetBrandResponse>> getAll();

	Result add(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(int id);
}