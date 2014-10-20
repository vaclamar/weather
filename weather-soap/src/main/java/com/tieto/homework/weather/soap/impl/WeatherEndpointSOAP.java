package com.tieto.homework.weather.soap.impl;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.WeatherRequest;
import com.tieto.homework.weather.WeatherResponse;
import com.tieto.homework.weather.dto.CityWeatherDTO;

/**
 * Endpoint for SOAP calls.
 */
//@Controller
@Endpoint
public class WeatherEndpointSOAP {

	private static final Logger logger = LoggerFactory.getLogger(WeatherEndpointSOAP.class);
	
	private static final String NAMESPACE_URI = "http://weather.homework.tieto.com/schemas";
	
	@Autowired
	private WeatherResponseMapper responseMapper;
	
	@Autowired
	private CityWeatherTypeMapper cityWeatherMapper;
	
	@Autowired
	private IWeatherService service;	
	
	/**
	 * Endpoint method for getting weather data.
	 * If empty city list is provided in request then are returned data for supported cities.
	 * 
	 * @param weatherRequest Contains list of cities.
	 * @return Weather data for cities from request or for supported cities.
	 * @throws ServerError 
	 * @throws ClientError 
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "WeatherRequest")
	@ResponsePayload	
	public WeatherResponse handleWeatherRequest(@RequestPayload WeatherRequest weatherRequest) /*throws ServerException , ClientException */{
		
		List<CityWeatherDTO> response;
		WeatherResponse result;
		
		if(weatherRequest.getCity().isEmpty()) {
			LoggerFactory.getLogger(WeatherEndpointSOAP.class).info("SOAP Request for all cities.");
			response = service.getAllWeatherData();
		} else {
			response = new ArrayList<CityWeatherDTO>();
			LoggerFactory.getLogger(WeatherEndpointSOAP.class).info("SOAP Request for city: " + weatherRequest.getCity());
			for (String city : weatherRequest.getCity()) {
				response.add(service.getWeatherData(city));				
			}
		}
		result = responseMapper.mapWeatherResponse(response, new WeatherResponse());
		
		logger.info("SOAP Request completed.");
		
		return result;
	}	
}
