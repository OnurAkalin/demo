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

        Model bmw1 = new Model();
        bmw1.setName("120");
        bmw1.setBrand(bmw);

        Model bmw2 = new Model();
        bmw2.setName("220");
        bmw2.setBrand(bmw);

        Model bmw3 = new Model();
        bmw3.setName("320");
        bmw3.setBrand(bmw);

        Model bmw4 = new Model();
        bmw4.setName("420");
        bmw4.setBrand(bmw);

        Model bmw5 = new Model();
        bmw5.setName("520");
        bmw5.setBrand(bmw);

        Model bmw6 = new Model();
        bmw6.setName("620");
        bmw6.setBrand(bmw);

        Model bmw7 = new Model();
        bmw7.setName("720");
        bmw7.setBrand(bmw);

        Model bmw8 = new Model();
        bmw8.setName("820");
        bmw8.setBrand(bmw);

        Model bmw9 = new Model();
        bmw9.setName("920");
        bmw9.setBrand(bmw);

        Model a1 = new Model();
        a1.setName("A1");
        a1.setBrand(audi);

        Model a2 = new Model();
        a2.setName("A2");
        a2.setBrand(audi);

        Model a3 = new Model();
        a3.setName("A3");
        a3.setBrand(audi);

        Model a4 = new Model();
        a4.setName("A4");
        a4.setBrand(audi);

        Model a5 = new Model();
        a5.setName("A5");
        a5.setBrand(audi);

        Model a6 = new Model();
        a6.setName("A6");
        a6.setBrand(audi);

        Model a7 = new Model();
        a7.setName("A7");
        a7.setBrand(audi);

        Model a8 = new Model();
        a8.setName("A8");
        a8.setBrand(audi);

        Model a9 = new Model();
        a9.setName("A9");
        a9.setBrand(audi);

        modelRepository.saveAll(List.of(bmw1, bmw2, bmw3, bmw4, bmw5, bmw6, bmw7, bmw8, bmw9,
                a1, a2, a3, a4, a5, a6, a7, a8, a9));
        log.info("Brands and Models initialized");
    }
}