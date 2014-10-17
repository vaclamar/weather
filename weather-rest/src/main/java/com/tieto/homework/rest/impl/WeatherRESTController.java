package com.tieto.homework.rest.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.weather.exception.ClientException;
import com.tieto.homework.weather.exception.ErrorCodes;
import com.tieto.homework.weather.exception.ServerException;

@Controller
public class WeatherRESTController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherRESTController.class);
	
	public static Map<Integer, HttpStatus> HTTP_CODE_MAPPING;
	
	static {
		HTTP_CODE_MAPPING = new HashMap<Integer, HttpStatus>();
		HTTP_CODE_MAPPING.put(ErrorCodes.NOT_SUPPORTED_CITY, HttpStatus.NOT_FOUND);
		HTTP_CODE_MAPPING.put(ErrorCodes.WUNDERGROUND_CALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Autowired
	private IWeatherService service;
	
	@RequestMapping(value="/simpleTest", method=RequestMethod.GET)
	@ResponseBody
	public String simpleTest() {		
		return "REST simple test";
	}
	
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	@ResponseBody	
	public List<CityWeatherDTO> getWeatherData() {
		List<CityWeatherDTO> ret = service.getAllWeatherData();
		return ret;
	}
	
	@RequestMapping(value="/weather/{city}", method=RequestMethod.GET)
	@ResponseBody	
	public CityWeatherDTO getWeatherData(@PathVariable("city") String city) {
		CityWeatherDTO ret = service.getWeatherData(city);
		return ret;
	}
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<String> handleException(ClientException e) {
		logger.debug("ClientException handled",e);
		ResponseEntity<String> response = new ResponseEntity<String>(ErrorCodes.MSG.get(e.getErrorCode()), HTTP_CODE_MAPPING.get(e.getErrorCode()));		
	    return response;
	}
	
	@ExceptionHandler(ServerException.class)
	public ResponseEntity<String> handleException(ServerException e) {
		logger.debug("ServerException handled",e);
		ResponseEntity<String> response;
		if(logger.isDebugEnabled()) {
			response = new ResponseEntity<String>(ErrorCodes.MSG.get(e.getErrorCode()), HTTP_CODE_MAPPING.get(e.getErrorCode()));	
		} else {
			response = new ResponseEntity<String>("Unexpected problem on the server. Please, try it later or conntact support", HTTP_CODE_MAPPING.get(e.getErrorCode()));
		}
	    return response;
	}
		


}
