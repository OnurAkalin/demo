package com.example.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum Status {
    ACTIVE(1),
    PASSIVE(2),
    DELETED(3);

    private final int code;
}
