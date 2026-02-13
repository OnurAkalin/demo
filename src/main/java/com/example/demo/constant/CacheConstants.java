package com.example.demo.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CacheConstants {
    public static final String DEFAULT = "default";
    public static final int DEFAULT_CACHE_DURATION_MIN = 30;

    public static final String BRANDS = "brands";
    public static final int BRANDS_CACHE_DURATION_MIN = 5;

    public static final String MODELS = "models";
    public static final int MODELS_CACHE_DURATION_MIN = 2;


    public static final String ALL_KEY = "'all'";
}
