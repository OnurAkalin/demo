package com.example.demo.webApi.controllers;

import com.example.demo.business.ModelService;
import com.example.demo.business.requests.CreateModelRequest;
import com.example.demo.business.requests.UpdateModelRequest;
import com.example.demo.business.responses.GetModelDetailsResponse;
import com.example.demo.business.responses.GetModelResponse;
import com.example.demo.common.result.DataResult;
import com.example.demo.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/models")
@RequiredArgsConstructor
public class ModelsController {
	private final ModelService modelService;

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<DataResult<GetModelDetailsResponse>> getById(@PathVariable int id) {
		return ResponseEntity.ok(modelService.getById(id));
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<DataResult<List<GetModelResponse>>> getAll() {
		return ResponseEntity.ok(modelService.getAll());
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Result> add(@RequestBody CreateModelRequest createModelRequest) {
		return ResponseEntity.ok(modelService.add(createModelRequest));
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Result> update(@RequestBody UpdateModelRequest updateModelRequest) {
		return ResponseEntity.ok(modelService.update(updateModelRequest));
	}
}
