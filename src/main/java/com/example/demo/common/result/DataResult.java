package com.example.demo.common.result;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DataResult<T> extends Result {
	private T data;

	protected DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}

	protected DataResult(T data, boolean success, String message) {
		super(success, message);
		this.data = data;
	}
}
