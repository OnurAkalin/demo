package com.example.demo.controllers;

import com.example.demo.dtos.requests.CreateBrandRequest;
import com.example.demo.dtos.requests.UpdateBrandRequest;
import com.example.demo.dtos.responses.GetBrandDetailsResponse;
import com.example.demo.dtos.responses.GetBrandResponse;
import com.example.demo.services.BrandService;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class BrandsController {
    private final BrandService brandService;

    @GetMapping(path = "/get/{id}")
    public DataResult<GetBrandDetailsResponse> getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @GetMapping(path = "/getAll")
    public DataResult<List<GetBrandResponse>> getAll() {
        return brandService.getAll();
    }

    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @PostMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return brandService.update(updateBrandRequest);
    }

    @PostMapping(path = "/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return brandService.delete(id);
    }
}
