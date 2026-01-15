package com.example.demo.jobs;

import com.example.demo.constants.KafkaConstants;
import com.example.demo.services.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoJobs {
    private final KafkaProducerService kafkaProducer;

    @Scheduled(fixedRateString = "1h", initialDelayString = "30s")
    public void jobClearCache() {
        kafkaProducer.sendMessage("all", KafkaConstants.CACHE_TOPIC);
    }
}
