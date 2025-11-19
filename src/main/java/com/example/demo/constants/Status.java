package com.example.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE(1),
    DELETED(2);

    private final int code;
}
