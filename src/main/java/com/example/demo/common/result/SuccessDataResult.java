package com.example.demo.common.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuccessDataResult<T> extends DataResult<T> {
	public SuccessDataResult(T data) {
		super(data, true);
	}

	public SuccessDataResult(T data, String message) {
		super(data, true, message);
	}
}
