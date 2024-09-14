package com.example.demo.webApi.controllers;

import com.example.demo.business.BrandService;
import com.example.demo.business.requests.CreateBrandRequest;
import com.example.demo.business.requests.UpdateBrandRequest;
import com.example.demo.business.responses.GetBrandDetailsResponse;
import com.example.demo.business.responses.GetBrandResponse;
import com.example.demo.common.result.DataResult;
import com.example.demo.common.result.Result;
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
	public ResponseEntity<DataResult<GetBrandDetailsResponse>> getById(@PathVariable int id) {
		return ResponseEntity.ok(brandService.getById(id));
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<DataResult<List<GetBrandResponse>>> getAll() {
		return ResponseEntity.ok(brandService.getAll());
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Result> add(@RequestBody CreateBrandRequest createBrandRequest) {
		return ResponseEntity.ok(brandService.add(createBrandRequest));
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Result> update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return ResponseEntity.ok(brandService.update(updateBrandRequest));
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<Result> delete(@PathVariable int id) {
		return ResponseEntity.ok(brandService.delete(id));
	}
}
