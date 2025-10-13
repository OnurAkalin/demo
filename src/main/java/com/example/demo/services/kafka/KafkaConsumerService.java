package com.example.demo.services.kafka;

import com.example.demo.constants.CacheConstants;
import com.example.demo.constants.KafkaConstants;
import com.example.demo.services.BrandService;
import com.example.demo.services.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {
    private final BrandService brandService;
    private final ModelService modelService;
    private final CacheManager cacheManager;

    @KafkaListener(topics = KafkaConstants.DATABASE_TOPIC, groupId = KafkaConstants.DATABASE_GROUP)
    public void clearDatabase(String message) {

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

    @KafkaListener(topics = KafkaConstants.CACHE_TOPIC, groupId = KafkaConstants.CACHE_GROUP)
    public void clearCache(String message) {

        switch (message) {
            case CacheConstants.BRANDS -> {
                Objects.requireNonNull(cacheManager.getCache(CacheConstants.BRANDS)).clear();
                log.info("All brands deleted from cache");
            }
            case CacheConstants.MODELS -> {
                Objects.requireNonNull(cacheManager.getCache(CacheConstants.MODELS)).clear();
                log.info("All models deleted from cache");
            }
            case "all" -> {
                Objects.requireNonNull(cacheManager.getCache(CacheConstants.BRANDS)).clear();
                Objects.requireNonNull(cacheManager.getCache(CacheConstants.MODELS)).clear();
                log.info("All entries deleted from cache");
            }
        }
    }
}
