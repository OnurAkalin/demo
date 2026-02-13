package com.example.demo.config;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Model;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (isDatabaseAlreadyInitialized()) {
            log.info("Database already initialized, skipping data initialization");
            return;
        }

        initBrandsAndModels();
    }

    private boolean isDatabaseAlreadyInitialized() {
        return brandRepository.count() > 0;
    }

    private void initBrandsAndModels() {
        log.info("Initializing brands and models...");

        Map<String, List<String>> brandModelData = getBrandModelData();
        List<Brand> savedBrands = createAndSaveBrands(brandModelData.keySet());
        createAndSaveModels(savedBrands, brandModelData);

        log.info("Successfully initialized {} brands with their models", savedBrands.size());
    }

    private List<Brand> createAndSaveBrands(Iterable<String> brandNames) {
        List<Brand> brands = new ArrayList<>();
        for (String brandName : brandNames) {
            Brand brand = new Brand();
            brand.setName(brandName);
            brands.add(brand);
        }
        return brandRepository.saveAll(brands);
    }

    private void createAndSaveModels(List<Brand> brands, Map<String, List<String>> brandModelData) {
        List<Model> allModels = brands.stream()
                .filter(brand -> brandModelData.containsKey(brand.getName()))
                .flatMap(brand -> createModelsForBrand(brand, brandModelData.get(brand.getName())).stream())
                .toList();

        modelRepository.saveAll(allModels);
    }

    private List<Model> createModelsForBrand(Brand brand, List<String> modelNames) {
        return modelNames.stream()
                .map(modelName -> createModel(modelName, brand))
                .toList();
    }

    private Model createModel(String modelName, Brand brand) {
        Model model = new Model();
        model.setName(modelName);
        model.setBrand(brand);
        return model;
    }

    private Map<String, List<String>> getBrandModelData() {
        return Map.ofEntries(
                Map.entry("Toyota", List.of("Corolla", "Camry", "RAV4", "Yaris", "C-HR", "Hilux", "Prius")),
                Map.entry("Volkswagen", List.of("Golf", "Polo", "Passat", "Tiguan", "T-Roc", "Arteon", "ID.3")),
                Map.entry("Ford", List.of("Fiesta", "Focus", "Mondeo", "Kuga", "Puma", "Mustang", "Ranger")),
                Map.entry("Honda", List.of("Civic", "Accord", "CR-V", "HR-V", "Jazz", "City")),
                Map.entry("Chevrolet", List.of("Cruze", "Malibu", "Tahoe", "Suburban", "Camaro", "Spark")),
                Map.entry("Mercedes-Benz", List.of("A-Class", "C-Class", "E-Class", "S-Class", "GLA", "GLC", "GLE")),
                Map.entry("BMW", List.of("1 Series", "2 Series", "3 Series", "4 Series", "5 Series", "X1", "X3", "X5")),
                Map.entry("Audi", List.of("A1", "A3", "A4", "A6", "Q2", "Q5", "Q7", "e-tron")),
                Map.entry("Hyundai", List.of("i10", "i20", "i30", "Elantra", "Tucson", "Santa Fe", "Kona")),
                Map.entry("Kia", List.of("Rio", "Ceed", "Cerato", "Sportage", "Sorento", "Stonic", "EV6")),
                Map.entry("Peugeot", List.of("208", "2008", "3008", "308", "5008")),
                Map.entry("Renault", List.of("Clio", "Megane", "Talisman", "Captur", "Kadjar", "Austral", "Zoe")),
                Map.entry("Nissan", List.of("Micra", "Qashqai", "X-Trail", "Juke", "Leaf")),
                Map.entry("Volvo", List.of("XC40", "XC60", "XC90", "S60", "V60", "C40")),
                Map.entry("Skoda", List.of("Fabia", "Scala", "Octavia", "Superb", "Karoq", "Kodiaq", "Enyaq")),
                Map.entry("Seat", List.of("Ibiza", "Leon", "Arona", "Ateca", "Tarraco")),
                Map.entry("Fiat", List.of("500", "Panda", "Tipo", "Egea", "Punto")),
                Map.entry("Opel", List.of("Corsa", "Astra", "Insignia", "Mokka", "Crossland", "Grandland")),
                Map.entry("Mazda", List.of("Mazda2", "Mazda3", "Mazda6", "CX-3", "CX-30", "CX-5")),
                Map.entry("Subaru", List.of("Impreza", "Legacy", "Forester", "Outback", "XV", "BRZ")),
                Map.entry("Mitsubishi", List.of("Lancer", "ASX", "Outlander", "Eclipse Cross", "Pajero")),
                Map.entry("Porsche", List.of("911", "Cayman", "Boxster", "Panamera", "Macan", "Cayenne", "Taycan")),
                Map.entry("Jaguar", List.of("XE", "XF", "F-Pace", "E-Pace", "I-Pace", "F-Type")),
                Map.entry("Land Rover", List.of("Defender", "Discovery", "Discovery Sport", "Range Rover", "Range Rover Sport", "Evoque")),
                Map.entry("Mini", List.of("One", "Cooper", "Clubman", "Countryman")),
                Map.entry("Citroën", List.of("C3", "C4", "C4 Cactus", "C5 Aircross", "Berlingo")),
                Map.entry("Alfa Romeo", List.of("Giulietta", "Giulia", "Stelvio", "Tonale")),
                Map.entry("Dacia", List.of("Sandero", "Logan", "Duster", "Jogger", "Spring")),
                Map.entry("Jeep", List.of("Renegade", "Compass", "Cherokee", "Grand Cherokee", "Wrangler")),
                Map.entry("Tesla", List.of("Model 3", "Model Y", "Model S", "Model X", "Cybertruck"))
        );
    }
}