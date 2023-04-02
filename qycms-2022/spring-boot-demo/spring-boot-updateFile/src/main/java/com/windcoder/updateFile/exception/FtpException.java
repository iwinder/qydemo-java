package com.windcoder.updateFile.exception;

public class FtpException  extends RuntimeException {
	public FtpException(String message) {
		super(message);
	}

	public FtpException(String message, Exception cause) {
		super(message, cause);
	}
}
