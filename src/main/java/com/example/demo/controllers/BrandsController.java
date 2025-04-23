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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class BrandsController {
    private final BrandService brandService;

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<DataResult<GetBrandDetailsResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<DataResult<List<GetBrandResponse>>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Result> add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return ResponseEntity.ok(brandService.add(createBrandRequest));
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        return ResponseEntity.ok(brandService.update(updateBrandRequest));
    }

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.delete(id));
    }
}
