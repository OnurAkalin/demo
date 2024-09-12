package com.example.demo.common.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorDataResult<T> extends DataResult<T> {
	public ErrorDataResult(T data) {
		super(data, false);
	}

	public ErrorDataResult(T data, String message) {
		super(data, false, message);
	}
}
