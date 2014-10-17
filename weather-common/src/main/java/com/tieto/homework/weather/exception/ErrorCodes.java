package com.tieto.homework.weather.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {
	
	public static final int NOT_SUPPORTED_CITY = 1;
	public static final int WUNDERGROUND_CALL_FAILED = 2;
	public static final int EMPTY_WUNDERGROUND_CACHE = 3;
	public static final Map<Integer, String> MSG = new HashMap<Integer, String>();
	
	private ErrorCodes(){}
	
	static {
		MSG.put(NOT_SUPPORTED_CITY, "Unsupported city");
		MSG.put(WUNDERGROUND_CALL_FAILED, "Wunderground call failed.");
		MSG.put(WUNDERGROUND_CALL_FAILED, "Empty wunderground cache");
	}
}
