package com.example.demo.configurations;

import com.example.demo.constants.CacheConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

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

    private RedisCacheConfiguration createDefaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CacheConstants.DEFAULT_CACHE_DURATION_MIN))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
    }

    private RedisCacheConfiguration createCacheConfiguration(int durationInMinutes) {
        return createDefaultCacheConfiguration()
                .entryTtl(Duration.ofMinutes(durationInMinutes));
    }
}