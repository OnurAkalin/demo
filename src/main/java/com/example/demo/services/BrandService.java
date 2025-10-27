package com.example.demo.services;

import com.example.demo.dtos.requests.CreateBrandRequest;
import com.example.demo.dtos.requests.UpdateBrandRequest;
import com.example.demo.dtos.responses.GetBrandDetailsResponse;
import com.example.demo.dtos.responses.GetBrandResponse;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;

import java.util.List;

public interface BrandService {
    DataResult<GetBrandDetailsResponse> getById(Long id);

    DataResult<List<GetBrandResponse>> getAll();

    Result add(CreateBrandRequest createBrandRequest);

    Result update(UpdateBrandRequest updateBrandRequest);

    Result delete(Long id);

    void hardDeleteAll();
}