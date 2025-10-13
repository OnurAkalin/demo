package com.example.demo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaConstants {
    public static final String DATABASE_TOPIC = "database-topic";
    public static final String DATABASE_GROUP = "database-group";

    public static final String CACHE_TOPIC = "cache-topic";
    public static final String CACHE_GROUP = "cache-group";
}
