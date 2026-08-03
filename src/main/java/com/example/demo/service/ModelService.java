package com.example.demo.service;

import com.example.demo.dto.request.CreateModelRequest;
import com.example.demo.dto.request.UpdateModelRequest;
import com.example.demo.dto.response.GetModelDetailsResponse;
import com.example.demo.dto.response.GetModelResponse;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;

import java.util.List;

public interface ModelService {
    DataResult<GetModelDetailsResponse> getById(Long id);

    DataResult<List<GetModelResponse>> getAll();

    DataResult<List<GetModelResponse>> getAllByBrandId(Long brandId);

    DataResult<PagedResponse<GetModelResponse>> getAllPaged(int pageNo);

    Result add(CreateModelRequest createModelRequest);

    Result update(UpdateModelRequest updateModelRequest);

    Result delete(Long id);

    void hardDeleteAll();
}
