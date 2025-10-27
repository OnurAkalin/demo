package com.example.demo.converters;

import com.example.demo.constants.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}