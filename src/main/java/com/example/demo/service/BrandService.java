package com.example.demo.service;

import com.example.demo.dto.request.CreateBrandRequest;
import com.example.demo.dto.request.UpdateBrandRequest;
import com.example.demo.dto.response.GetBrandDetailsResponse;
import com.example.demo.dto.response.GetBrandResponse;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;

import java.util.List;

public interface BrandService {
    DataResult<GetBrandDetailsResponse> getById(Long id);

    DataResult<List<GetBrandResponse>> getAll();

    Result add(CreateBrandRequest createBrandRequest);

    Result update(UpdateBrandRequest updateBrandRequest);

    Result delete(Long id);

    void hardDeleteAll();
}