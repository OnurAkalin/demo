package com.example.demo.business;

import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.business.requests.UpdateModelRequest;
import com.example.demo.business.responses.GetModelDetailsResponse;
import com.example.demo.business.responses.GetModelResponse;
import com.example.demo.common.result.DataResult;
import com.example.demo.common.result.Result;

import java.util.List;

public interface ModelService {
	DataResult<GetModelDetailsResponse> getById(int id);

	DataResult<List<GetModelResponse>> getAll();

	Result add(CreateModelRequest createModelRequest);

	Result update(UpdateModelRequest updateModelRequest);

	Result delete(int id);
}
