package com.example.demo.common.result;

public final class ErrorResult extends Result {
	public ErrorResult() {
		super(false);
	}

	public ErrorResult(String message) {
		super(false, message);
	}
}
