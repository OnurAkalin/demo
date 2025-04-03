package com.example.demo.controllers;

import com.example.demo.services.ModelService;
import com.example.demo.dtos.requests.CreateModelRequest;
import com.example.demo.dtos.requests.UpdateModelRequest;
import com.example.demo.dtos.responses.GetModelDetailsResponse;
import com.example.demo.dtos.responses.GetModelResponse;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;
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
    public ResponseEntity<DataResult<GetModelDetailsResponse>> getById(@PathVariable Long id) {
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

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.delete(id));
    }
}
