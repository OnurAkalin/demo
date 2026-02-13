package com.example.demo.job;

import com.example.demo.constant.KafkaConstants;
import com.example.demo.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoJob {
    private final KafkaProducerService kafkaProducer;

    @Scheduled(fixedRateString = "1h", initialDelayString = "30d")
    public void jobClearCache() {
        kafkaProducer.sendMessage("all", KafkaConstants.CACHE_TOPIC);
    }
}
