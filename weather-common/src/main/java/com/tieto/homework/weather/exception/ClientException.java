package com.tieto.homework.weather.exception;

public class ClientException extends RuntimeException {

	private static final long serialVersionUID = 1472259044279851288L;
	
	private int errorCode;

	public ClientException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ClientException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
