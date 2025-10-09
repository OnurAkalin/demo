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
        List<Brand> brands = brandRepository.saveAll(List.of(
                Brand.builder().name("Toyota").build(),
                Brand.builder().name("Volkswagen").build(),
                Brand.builder().name("Ford").build(),
                Brand.builder().name("Honda").build(),
                Brand.builder().name("Chevrolet").build(),
                Brand.builder().name("Mercedes-Benz").build(),
                Brand.builder().name("BMW").build(),
                Brand.builder().name("Audi").build(),
                Brand.builder().name("Hyundai").build(),
                Brand.builder().name("Kia").build(),
                Brand.builder().name("Peugeot").build(),
                Brand.builder().name("Renault").build(),
                Brand.builder().name("Nissan").build(),
                Brand.builder().name("Volvo").build(),
                Brand.builder().name("Skoda").build(),
                Brand.builder().name("Seat").build(),
                Brand.builder().name("Fiat").build(),
                Brand.builder().name("Opel").build(),
                Brand.builder().name("Mazda").build(),
                Brand.builder().name("Subaru").build(),
                Brand.builder().name("Mitsubishi").build(),
                Brand.builder().name("Porsche").build(),
                Brand.builder().name("Jaguar").build(),
                Brand.builder().name("Land Rover").build(),
                Brand.builder().name("Mini").build(),
                Brand.builder().name("Citroën").build(),
                Brand.builder().name("Alfa Romeo").build(),
                Brand.builder().name("Dacia").build(),
                Brand.builder().name("Jeep").build(),
                Brand.builder().name("Tesla").build()
        ));

        Brand toyota = brands.get(0);
        Brand volkswagen = brands.get(1);
        Brand ford = brands.get(2);
        Brand honda = brands.get(3);
        Brand chevrolet = brands.get(4);
        Brand mercedes = brands.get(5);
        Brand bmw = brands.get(6);
        Brand audi = brands.get(7);
        Brand hyundai = brands.get(8);
        Brand kia = brands.get(9);
        Brand peugeot = brands.get(10);
        Brand renault = brands.get(11);
        Brand nissan = brands.get(12);
        Brand volvo = brands.get(13);
        Brand skoda = brands.get(14);
        Brand seat = brands.get(15);
        Brand fiat = brands.get(16);
        Brand opel = brands.get(17);
        Brand mazda = brands.get(18);
        Brand subaru = brands.get(19);
        Brand mitsubishi = brands.get(20);
        Brand porsche = brands.get(21);
        Brand jaguar = brands.get(22);
        Brand landRover = brands.get(23);
        Brand mini = brands.get(24);
        Brand citroen = brands.get(25);
        Brand alfaRomeo = brands.get(26);
        Brand dacia = brands.get(27);
        Brand jeep = brands.get(28);
        Brand tesla = brands.get(29);

        List<Model> models = new ArrayList<>();

        // Toyota
        models.add(Model.builder().name("Corolla").brand(toyota).build());
        models.add(Model.builder().name("Camry").brand(toyota).build());
        models.add(Model.builder().name("RAV4").brand(toyota).build());
        models.add(Model.builder().name("Yaris").brand(toyota).build());
        models.add(Model.builder().name("C-HR").brand(toyota).build());
        models.add(Model.builder().name("Hilux").brand(toyota).build());
        models.add(Model.builder().name("Prius").brand(toyota).build());

        // Volkswagen
        models.add(Model.builder().name("Golf").brand(volkswagen).build());
        models.add(Model.builder().name("Polo").brand(volkswagen).build());
        models.add(Model.builder().name("Passat").brand(volkswagen).build());
        models.add(Model.builder().name("Tiguan").brand(volkswagen).build());
        models.add(Model.builder().name("T-Roc").brand(volkswagen).build());
        models.add(Model.builder().name("Arteon").brand(volkswagen).build());
        models.add(Model.builder().name("ID.3").brand(volkswagen).build());

        // Ford
        models.add(Model.builder().name("Fiesta").brand(ford).build());
        models.add(Model.builder().name("Focus").brand(ford).build());
        models.add(Model.builder().name("Mondeo").brand(ford).build());
        models.add(Model.builder().name("Kuga").brand(ford).build());
        models.add(Model.builder().name("Puma").brand(ford).build());
        models.add(Model.builder().name("Mustang").brand(ford).build());
        models.add(Model.builder().name("Ranger").brand(ford).build());

        // Honda
        models.add(Model.builder().name("Civic").brand(honda).build());
        models.add(Model.builder().name("Accord").brand(honda).build());
        models.add(Model.builder().name("CR-V").brand(honda).build());
        models.add(Model.builder().name("HR-V").brand(honda).build());
        models.add(Model.builder().name("Jazz").brand(honda).build());
        models.add(Model.builder().name("City").brand(honda).build());

        // Chevrolet
        models.add(Model.builder().name("Cruze").brand(chevrolet).build());
        models.add(Model.builder().name("Malibu").brand(chevrolet).build());
        models.add(Model.builder().name("Tahoe").brand(chevrolet).build());
        models.add(Model.builder().name("Suburban").brand(chevrolet).build());
        models.add(Model.builder().name("Camaro").brand(chevrolet).build());
        models.add(Model.builder().name("Spark").brand(chevrolet).build());

        // Mercedes-Benz
        models.add(Model.builder().name("A-Class").brand(mercedes).build());
        models.add(Model.builder().name("C-Class").brand(mercedes).build());
        models.add(Model.builder().name("E-Class").brand(mercedes).build());
        models.add(Model.builder().name("S-Class").brand(mercedes).build());
        models.add(Model.builder().name("GLA").brand(mercedes).build());
        models.add(Model.builder().name("GLC").brand(mercedes).build());
        models.add(Model.builder().name("GLE").brand(mercedes).build());

        // BMW
        models.add(Model.builder().name("1 Series").brand(bmw).build());
        models.add(Model.builder().name("2 Series").brand(bmw).build());
        models.add(Model.builder().name("3 Series").brand(bmw).build());
        models.add(Model.builder().name("4 Series").brand(bmw).build());
        models.add(Model.builder().name("5 Series").brand(bmw).build());
        models.add(Model.builder().name("X1").brand(bmw).build());
        models.add(Model.builder().name("X3").brand(bmw).build());
        models.add(Model.builder().name("X5").brand(bmw).build());

        // Audi
        models.add(Model.builder().name("A1").brand(audi).build());
        models.add(Model.builder().name("A3").brand(audi).build());
        models.add(Model.builder().name("A4").brand(audi).build());
        models.add(Model.builder().name("A6").brand(audi).build());
        models.add(Model.builder().name("Q2").brand(audi).build());
        models.add(Model.builder().name("Q5").brand(audi).build());
        models.add(Model.builder().name("Q7").brand(audi).build());
        models.add(Model.builder().name("e-tron").brand(audi).build());

        // Hyundai
        models.add(Model.builder().name("i10").brand(hyundai).build());
        models.add(Model.builder().name("i20").brand(hyundai).build());
        models.add(Model.builder().name("i30").brand(hyundai).build());
        models.add(Model.builder().name("Elantra").brand(hyundai).build());
        models.add(Model.builder().name("Tucson").brand(hyundai).build());
        models.add(Model.builder().name("Santa Fe").brand(hyundai).build());
        models.add(Model.builder().name("Kona").brand(hyundai).build());

        // Kia
        models.add(Model.builder().name("Rio").brand(kia).build());
        models.add(Model.builder().name("Ceed").brand(kia).build());
        models.add(Model.builder().name("Cerato").brand(kia).build());
        models.add(Model.builder().name("Sportage").brand(kia).build());
        models.add(Model.builder().name("Sorento").brand(kia).build());
        models.add(Model.builder().name("Stonic").brand(kia).build());
        models.add(Model.builder().name("EV6").brand(kia).build());

        // Peugeot
        models.add(Model.builder().name("208").brand(peugeot).build());
        models.add(Model.builder().name("2008").brand(peugeot).build());
        models.add(Model.builder().name("3008").brand(peugeot).build());
        models.add(Model.builder().name("308").brand(peugeot).build());
        models.add(Model.builder().name("5008").brand(peugeot).build());

        // Renault
        models.add(Model.builder().name("Clio").brand(renault).build());
        models.add(Model.builder().name("Megane").brand(renault).build());
        models.add(Model.builder().name("Talisman").brand(renault).build());
        models.add(Model.builder().name("Captur").brand(renault).build());
        models.add(Model.builder().name("Kadjar").brand(renault).build());
        models.add(Model.builder().name("Austral").brand(renault).build());
        models.add(Model.builder().name("Zoe").brand(renault).build());

        // Nissan
        models.add(Model.builder().name("Micra").brand(nissan).build());
        models.add(Model.builder().name("Qashqai").brand(nissan).build());
        models.add(Model.builder().name("X-Trail").brand(nissan).build());
        models.add(Model.builder().name("Juke").brand(nissan).build());
        models.add(Model.builder().name("Leaf").brand(nissan).build());

        // Volvo
        models.add(Model.builder().name("XC40").brand(volvo).build());
        models.add(Model.builder().name("XC60").brand(volvo).build());
        models.add(Model.builder().name("XC90").brand(volvo).build());
        models.add(Model.builder().name("S60").brand(volvo).build());
        models.add(Model.builder().name("V60").brand(volvo).build());
        models.add(Model.builder().name("C40").brand(volvo).build());

        // Skoda
        models.add(Model.builder().name("Fabia").brand(skoda).build());
        models.add(Model.builder().name("Scala").brand(skoda).build());
        models.add(Model.builder().name("Octavia").brand(skoda).build());
        models.add(Model.builder().name("Superb").brand(skoda).build());
        models.add(Model.builder().name("Karoq").brand(skoda).build());
        models.add(Model.builder().name("Kodiaq").brand(skoda).build());
        models.add(Model.builder().name("Enyaq").brand(skoda).build());

        // Seat
        models.add(Model.builder().name("Ibiza").brand(seat).build());
        models.add(Model.builder().name("Leon").brand(seat).build());
        models.add(Model.builder().name("Arona").brand(seat).build());
        models.add(Model.builder().name("Ateca").brand(seat).build());
        models.add(Model.builder().name("Tarraco").brand(seat).build());

        // Fiat
        models.add(Model.builder().name("500").brand(fiat).build());
        models.add(Model.builder().name("Panda").brand(fiat).build());
        models.add(Model.builder().name("Tipo").brand(fiat).build());
        models.add(Model.builder().name("Egea").brand(fiat).build());
        models.add(Model.builder().name("Punto").brand(fiat).build());

        // Opel
        models.add(Model.builder().name("Corsa").brand(opel).build());
        models.add(Model.builder().name("Astra").brand(opel).build());
        models.add(Model.builder().name("Insignia").brand(opel).build());
        models.add(Model.builder().name("Mokka").brand(opel).build());
        models.add(Model.builder().name("Crossland").brand(opel).build());
        models.add(Model.builder().name("Grandland").brand(opel).build());

        // Mazda
        models.add(Model.builder().name("Mazda2").brand(mazda).build());
        models.add(Model.builder().name("Mazda3").brand(mazda).build());
        models.add(Model.builder().name("Mazda6").brand(mazda).build());
        models.add(Model.builder().name("CX-3").brand(mazda).build());
        models.add(Model.builder().name("CX-30").brand(mazda).build());
        models.add(Model.builder().name("CX-5").brand(mazda).build());

        // Subaru
        models.add(Model.builder().name("Impreza").brand(subaru).build());
        models.add(Model.builder().name("Legacy").brand(subaru).build());
        models.add(Model.builder().name("Forester").brand(subaru).build());
        models.add(Model.builder().name("Outback").brand(subaru).build());
        models.add(Model.builder().name("XV").brand(subaru).build());
        models.add(Model.builder().name("BRZ").brand(subaru).build());

        // Mitsubishi
        models.add(Model.builder().name("Lancer").brand(mitsubishi).build());
        models.add(Model.builder().name("ASX").brand(mitsubishi).build());
        models.add(Model.builder().name("Outlander").brand(mitsubishi).build());
        models.add(Model.builder().name("Eclipse Cross").brand(mitsubishi).build());
        models.add(Model.builder().name("Pajero").brand(mitsubishi).build());

        // Porsche
        models.add(Model.builder().name("911").brand(porsche).build());
        models.add(Model.builder().name("Cayman").brand(porsche).build());
        models.add(Model.builder().name("Boxster").brand(porsche).build());
        models.add(Model.builder().name("Panamera").brand(porsche).build());
        models.add(Model.builder().name("Macan").brand(porsche).build());
        models.add(Model.builder().name("Cayenne").brand(porsche).build());
        models.add(Model.builder().name("Taycan").brand(porsche).build());

        // Jaguar
        models.add(Model.builder().name("XE").brand(jaguar).build());
        models.add(Model.builder().name("XF").brand(jaguar).build());
        models.add(Model.builder().name("F-Pace").brand(jaguar).build());
        models.add(Model.builder().name("E-Pace").brand(jaguar).build());
        models.add(Model.builder().name("I-Pace").brand(jaguar).build());
        models.add(Model.builder().name("F-Type").brand(jaguar).build());

        // Land Rover
        models.add(Model.builder().name("Defender").brand(landRover).build());
        models.add(Model.builder().name("Discovery").brand(landRover).build());
        models.add(Model.builder().name("Discovery Sport").brand(landRover).build());
        models.add(Model.builder().name("Range Rover").brand(landRover).build());
        models.add(Model.builder().name("Range Rover Sport").brand(landRover).build());
        models.add(Model.builder().name("Evoque").brand(landRover).build());

        // Mini
        models.add(Model.builder().name("One").brand(mini).build());
        models.add(Model.builder().name("Cooper").brand(mini).build());
        models.add(Model.builder().name("Clubman").brand(mini).build());
        models.add(Model.builder().name("Countryman").brand(mini).build());

        // Citroën
        models.add(Model.builder().name("C3").brand(citroen).build());
        models.add(Model.builder().name("C4").brand(citroen).build());
        models.add(Model.builder().name("C4 Cactus").brand(citroen).build());
        models.add(Model.builder().name("C5 Aircross").brand(citroen).build());
        models.add(Model.builder().name("Berlingo").brand(citroen).build());

        // Alfa Romeo
        models.add(Model.builder().name("Giulietta").brand(alfaRomeo).build());
        models.add(Model.builder().name("Giulia").brand(alfaRomeo).build());
        models.add(Model.builder().name("Stelvio").brand(alfaRomeo).build());
        models.add(Model.builder().name("Tonale").brand(alfaRomeo).build());

        // Dacia
        models.add(Model.builder().name("Sandero").brand(dacia).build());
        models.add(Model.builder().name("Logan").brand(dacia).build());
        models.add(Model.builder().name("Duster").brand(dacia).build());
        models.add(Model.builder().name("Jogger").brand(dacia).build());
        models.add(Model.builder().name("Spring").brand(dacia).build());

        // Jeep
        models.add(Model.builder().name("Renegade").brand(jeep).build());
        models.add(Model.builder().name("Compass").brand(jeep).build());
        models.add(Model.builder().name("Cherokee").brand(jeep).build());
        models.add(Model.builder().name("Grand Cherokee").brand(jeep).build());
        models.add(Model.builder().name("Wrangler").brand(jeep).build());

        // Tesla
        models.add(Model.builder().name("Model 3").brand(tesla).build());
        models.add(Model.builder().name("Model Y").brand(tesla).build());
        models.add(Model.builder().name("Model S").brand(tesla).build());
        models.add(Model.builder().name("Model X").brand(tesla).build());
        models.add(Model.builder().name("Cybertruck").brand(tesla).build());

        modelRepository.saveAll(models);

        log.info("Marka ve model verileri yüklendi.");
    }
}