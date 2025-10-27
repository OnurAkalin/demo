package com.example.demo.services;

import com.example.demo.dtos.requests.CreateModelRequest;
import com.example.demo.dtos.requests.UpdateModelRequest;
import com.example.demo.dtos.responses.GetModelDetailsResponse;
import com.example.demo.dtos.responses.GetModelResponse;
import com.example.demo.dtos.responses.PagedResponse;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;

import java.util.List;

public interface ModelService {
    DataResult<GetModelDetailsResponse> getById(Long id);

    DataResult<List<GetModelResponse>> getAll();

    DataResult<PagedResponse<GetModelResponse>> getAllPaged(int pageNo);

    Result add(CreateModelRequest createModelRequest);

    Result update(UpdateModelRequest updateModelRequest);

    Result delete(Long id);

    void hardDeleteAll();
}
