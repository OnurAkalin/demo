package com.example.demo.configurations;

import com.example.demo.entities.Brand;
import com.example.demo.entities.Model;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Override
    public void run(String... args) {
        if (brandRepository.count() == 0) {
            initBrandsAndModels();
        }
    }

    private void initBrandsAndModels() {
        Brand bmw = new Brand();
        bmw.setName("BMW");

        Brand audi = new Brand();
        audi.setName("Audi");

        brandRepository.saveAll(List.of(bmw, audi));

        Model bmw3 = new Model();
        bmw3.setName("320");
        bmw3.setBrand(bmw);

        Model bmw4 = new Model();
        bmw4.setName("420");
        bmw4.setBrand(bmw);

        Model bmw5 = new Model();
        bmw5.setName("520");
        bmw5.setBrand(bmw);

        Model a4 = new Model();
        a4.setName("A4");
        a4.setBrand(audi);

        Model a5 = new Model();
        a5.setName("A5");
        a5.setBrand(audi);

        Model a6 = new Model();
        a6.setName("A6");
        a6.setBrand(audi);

        modelRepository.saveAll(List.of(bmw3, bmw4, bmw5, a4, a5, a6));
        log.info("Brands and Models initialized");
    }
}