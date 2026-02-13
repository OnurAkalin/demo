package com.example.demo.controller;

import com.example.demo.dto.request.CreateModelRequest;
import com.example.demo.dto.request.UpdateModelRequest;
import com.example.demo.dto.response.GetModelDetailsResponse;
import com.example.demo.dto.response.GetModelResponse;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.service.ModelService;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping(path = "/get/{id}")
    public DataResult<GetModelDetailsResponse> getById(@PathVariable Long id) {
        return modelService.getById(id);
    }

    @GetMapping(path = "/getAll")
    public DataResult<List<GetModelResponse>> getAll() {
        return modelService.getAll();
    }

    @GetMapping(path = "/getAll/{pageNo}")
    public DataResult<PagedResponse<GetModelResponse>> getAllPaged(@PathVariable int pageNo) {
        return modelService.getAllPaged(pageNo);
    }

    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);
    }

    @PostMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateModelRequest updateModelRequest) {
        return modelService.update(updateModelRequest);
    }

    @PostMapping(path = "/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return modelService.delete(id);
    }
}
