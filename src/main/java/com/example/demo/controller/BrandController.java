package com.example.demo.controller;

import com.example.demo.dto.request.CreateBrandRequest;
import com.example.demo.dto.request.UpdateBrandRequest;
import com.example.demo.dto.response.GetBrandDetailsResponse;
import com.example.demo.dto.response.GetBrandResponse;
import com.example.demo.service.BrandService;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class BrandController {
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
