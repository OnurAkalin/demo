package com.example.demo.services.kafka;

import com.example.demo.constants.CacheConstants;
import com.example.demo.constants.KafkaConstants;
import com.example.demo.services.BrandService;
import com.example.demo.services.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {
    private final BrandService brandService;
    private final ModelService modelService;
    private final CacheManager cacheManager;

    private static final String ALL = "all";
    private static final String BRANDS = "brands";
    private static final String MODELS = "models";

    @KafkaListener(topics = KafkaConstants.DATABASE_TOPIC, groupId = KafkaConstants.DATABASE_GROUP)
    public void clearDatabase(String message) {
        switch (message) {
            case BRANDS -> {
                brandService.deleteAll();
                log.info("All brands deleted from database");
            }
            case MODELS -> {
                modelService.deleteAll();
                log.info("All models deleted from database");
            }
            case ALL -> {
                brandService.deleteAll();
                modelService.deleteAll();
                log.info("All data deleted from database");
            }
            default -> log.warn("Unknown database clear message: {}", message);
        }
    }

    @KafkaListener(topics = KafkaConstants.CACHE_TOPIC, groupId = KafkaConstants.CACHE_GROUP)
    public void clearCache(String message) {
        switch (message) {
            case CacheConstants.BRANDS -> clearSpecificCache(CacheConstants.BRANDS);
            case CacheConstants.MODELS -> clearSpecificCache(CacheConstants.MODELS);
            case ALL -> clearAllCaches();
            default -> log.warn("Unknown cache clear message: {}", message);
        }
    }

    private void clearSpecificCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            log.info("Cache '{}' cleared", cacheName);
        } else {
            log.error("Cache '{}' not found", cacheName);
        }
    }

    private void clearAllCaches() {
        cacheManager.getCacheNames().forEach(this::clearSpecificCache);
        log.info("All caches cleared");
    }
}