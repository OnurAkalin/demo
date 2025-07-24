package com.example.demo.services.kafka;

import com.example.demo.constants.KafkaConstants;
import com.example.demo.services.BrandService;
import com.example.demo.services.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final BrandService brandService;
    private final ModelService modelService;

    @KafkaListener(topics = KafkaConstants.DATABASE_TOPIC, groupId = KafkaConstants.DATABASE_GROUP)
    public void listen(String message) {

        switch (message) {
            case "brands" -> {
                brandService.deleteAll();
                log.info("All brands deleted");
            }
            case "models" -> {
                modelService.deleteAll();
                log.info("All models deleted");
            }
            case "all" -> {
                brandService.deleteAll();
                modelService.deleteAll();
                log.info("All tables deleted");
            }
        }
    }
}
