package com.example.demo.config;

import com.example.demo.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class RedisCacheConfig {
    @Value("${app.cache-prefix:demo:}")
    private String cachePrefix;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfig = createDefaultCacheConfiguration();

        Map<String, RedisCacheConfiguration> cacheConfigurations = Map.of(
                CacheConstants.BRANDS, createCacheConfiguration(CacheConstants.BRANDS_CACHE_DURATION_MIN),
                CacheConstants.MODELS, createCacheConfiguration(CacheConstants.MODELS_CACHE_DURATION_MIN),
                CacheConstants.DEFAULT, createCacheConfiguration(CacheConstants.DEFAULT_CACHE_DURATION_MIN)
        );

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware()
                .build();
    }

    private RedisCacheConfiguration createCacheConfiguration(int durationInMinutes) {
        int jitter = ThreadLocalRandom.current().nextInt(0, 3);
        return createDefaultCacheConfiguration()
                .entryTtl(Duration.ofMinutes(durationInMinutes + jitter));
    }

    private RedisCacheConfiguration createDefaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CacheConstants.DEFAULT_CACHE_DURATION_MIN))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json())
                )
                .prefixCacheNameWith(cachePrefix);
    }
}