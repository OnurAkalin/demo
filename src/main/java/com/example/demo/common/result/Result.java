package com.example.demo.common.result;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Result {
	private boolean success;
	private String message;

	protected Result(boolean success) {
		this.success = success;
	}

	protected Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
}
