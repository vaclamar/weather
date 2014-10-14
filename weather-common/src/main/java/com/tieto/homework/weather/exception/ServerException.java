package com.tieto.homework.weather.exception;

public class ServerException extends RuntimeException {
	private static final long serialVersionUID = -5615996137703010513L;
	private int errorCode;

	public ServerException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ServerException(int errorCode, String message,Throwable throwable) {
		super(message,throwable);
		this.errorCode = errorCode;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
