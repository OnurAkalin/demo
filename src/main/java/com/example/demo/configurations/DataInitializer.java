package com.example.demo.configurations;

import com.example.demo.entities.Brand;
import com.example.demo.entities.Model;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Override
    public void run(String... args) {
        if (brandRepository.count() == 0) {
            log.info("Initializing brands and models...");
            initBrandsAndModels();
            log.info("Successfully initialized {} brands and models", brandRepository.count());
        } else {
            log.info("Database already initialized, skipping data initialization");
        }
    }

    private void initBrandsAndModels() {
        Map<String, List<String>> brandModelMap = createBrandModelData();
        List<Brand> savedBrands = createAndSaveBrands(brandModelMap.keySet());
        createAndSaveModels(savedBrands, brandModelMap);
    }

    private Map<String, List<String>> createBrandModelData() {
        Map<String, List<String>> brandModelMap = new HashMap<>();

        brandModelMap.put("Toyota", List.of("Corolla", "Camry", "RAV4", "Yaris", "C-HR", "Hilux", "Prius"));
        brandModelMap.put("Volkswagen", List.of("Golf", "Polo", "Passat", "Tiguan", "T-Roc", "Arteon", "ID.3"));
        brandModelMap.put("Ford", List.of("Fiesta", "Focus", "Mondeo", "Kuga", "Puma", "Mustang", "Ranger"));
        brandModelMap.put("Honda", List.of("Civic", "Accord", "CR-V", "HR-V", "Jazz", "City"));
        brandModelMap.put("Chevrolet", List.of("Cruze", "Malibu", "Tahoe", "Suburban", "Camaro", "Spark"));
        brandModelMap.put("Mercedes-Benz", List.of("A-Class", "C-Class", "E-Class", "S-Class", "GLA", "GLC", "GLE"));
        brandModelMap.put("BMW", List.of("1 Series", "2 Series", "3 Series", "4 Series", "5 Series", "X1", "X3", "X5"));
        brandModelMap.put("Audi", List.of("A1", "A3", "A4", "A6", "Q2", "Q5", "Q7", "e-tron"));
        brandModelMap.put("Hyundai", List.of("i10", "i20", "i30", "Elantra", "Tucson", "Santa Fe", "Kona"));
        brandModelMap.put("Kia", List.of("Rio", "Ceed", "Cerato", "Sportage", "Sorento", "Stonic", "EV6"));
        brandModelMap.put("Peugeot", List.of("208", "2008", "3008", "308", "5008"));
        brandModelMap.put("Renault", List.of("Clio", "Megane", "Talisman", "Captur", "Kadjar", "Austral", "Zoe"));
        brandModelMap.put("Nissan", List.of("Micra", "Qashqai", "X-Trail", "Juke", "Leaf"));
        brandModelMap.put("Volvo", List.of("XC40", "XC60", "XC90", "S60", "V60", "C40"));
        brandModelMap.put("Skoda", List.of("Fabia", "Scala", "Octavia", "Superb", "Karoq", "Kodiaq", "Enyaq"));
        brandModelMap.put("Seat", List.of("Ibiza", "Leon", "Arona", "Ateca", "Tarraco"));
        brandModelMap.put("Fiat", List.of("500", "Panda", "Tipo", "Egea", "Punto"));
        brandModelMap.put("Opel", List.of("Corsa", "Astra", "Insignia", "Mokka", "Crossland", "Grandland"));
        brandModelMap.put("Mazda", List.of("Mazda2", "Mazda3", "Mazda6", "CX-3", "CX-30", "CX-5"));
        brandModelMap.put("Subaru", List.of("Impreza", "Legacy", "Forester", "Outback", "XV", "BRZ"));
        brandModelMap.put("Mitsubishi", List.of("Lancer", "ASX", "Outlander", "Eclipse Cross", "Pajero"));
        brandModelMap.put("Porsche", List.of("911", "Cayman", "Boxster", "Panamera", "Macan", "Cayenne", "Taycan"));
        brandModelMap.put("Jaguar", List.of("XE", "XF", "F-Pace", "E-Pace", "I-Pace", "F-Type"));
        brandModelMap.put("Land Rover", List.of("Defender", "Discovery", "Discovery Sport", "Range Rover", "Range Rover Sport", "Evoque"));
        brandModelMap.put("Mini", List.of("One", "Cooper", "Clubman", "Countryman"));
        brandModelMap.put("Citroën", List.of("C3", "C4", "C4 Cactus", "C5 Aircross", "Berlingo"));
        brandModelMap.put("Alfa Romeo", List.of("Giulietta", "Giulia", "Stelvio", "Tonale"));
        brandModelMap.put("Dacia", List.of("Sandero", "Logan", "Duster", "Jogger", "Spring"));
        brandModelMap.put("Jeep", List.of("Renegade", "Compass", "Cherokee", "Grand Cherokee", "Wrangler"));
        brandModelMap.put("Tesla", List.of("Model 3", "Model Y", "Model S", "Model X", "Cybertruck"));

        return brandModelMap;
    }

    private List<Brand> createAndSaveBrands(Iterable<String> brandNames) {
        List<Brand> brands = new ArrayList<>();
        for (String brandName : brandNames) {
            brands.add(Brand.builder().name(brandName).build());
        }
        return brandRepository.saveAll(brands);
    }

    private void createAndSaveModels(List<Brand> brands, Map<String, List<String>> brandModelMap) {
        List<Model> allModels = new ArrayList<>();

        for (Brand brand : brands) {
            List<String> modelNames = brandModelMap.get(brand.getName());
            if (modelNames != null) {
                for (String modelName : modelNames) {
                    allModels.add(Model.builder()
                            .name(modelName)
                            .brand(brand)
                            .build());
                }
            }
        }

        modelRepository.saveAll(allModels);
    }
}