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

        if (message.equals("brands")) {
            brandService.deleteAll();
            log.info("Deleted all brands");
        } else if (message.equals("models")) {
            modelService.deleteAll();
            log.info("Deleted all models");
        }
    }
}
