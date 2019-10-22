package com.windcoder.jwtDemo.utils;

public class CommonResult<T> {
	private int status = 1;

	private String errorCode = "";

	private String errorMsg = "";

	private T resultBody;

	public CommonResult() {
	}

	public CommonResult(T resultBody) {
		this.resultBody = resultBody;
	}
}
