package com.tieto.homework.weather.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tieto.homework.weather.client.ICacheWeatherClient;
import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.weather.exception.ErrorCodes;
import com.tieto.homework.weather.exception.ServerException;
import com.tieto.homework.weather.mapper.WundergroundResponseMapper;
import com.tieto.homework.wunderground.Response;


@Component
public class CachedWundergroundClient implements ICacheWeatherClient {

	private static final Logger logger = LoggerFactory.getLogger(CachedWundergroundClient.class);	
	
//	@Resource(name="cityMap")
//	private Map<String, String> cityMap;
	
	@Value("${service.api.key}")	
	private String serviceApiKey;
	
	@Value("${service.url}")
	private String serviceUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WundergroundResponseMapper mapper;
	
	@Cacheable("cityWeather")
	public CityWeatherDTO getCityWeather(String state,String city) {
		CityWeatherDTO ret;
		ret = callCityWeather(state, city);
		return ret;
	}
	
	@CachePut("cityWeather")
	public CityWeatherDTO updateCityWeather(String state,String city) {
		CityWeatherDTO ret;
		ret = callCityWeather(state, city);
		return ret;
	}
	

	
	private CityWeatherDTO callCityWeather(String state,String city) /*throws ServerError*/ {

		logger.debug(String.format("Call Wunderground for: %s %s ",state, city));
		
		CityWeatherDTO result;
		try {
			Response wundergroundResponse = restTemplate.getForObject(serviceUrl, Response.class, serviceApiKey, state, city);
			result = mapper.mapWundergroundResponse(wundergroundResponse, new CityWeatherDTO());
		} catch (Exception ex) {
			throw new ServerException(ErrorCodes.WUNDERGROUND_CALL_FAILED, "Call Wunderground FAILED!", ex);
		}
		
		logger.debug(String.format("Successfully fetched data from Wunderground: %s %s",state,city));
		
		return result;
	}
}
