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

/**
 * Client with cache support for Wunderground web service.
 * 
 * @author vaclbmar
 *
 */
@Component
public class CachedWundergroundClient implements ICacheWeatherClient {

	private static final Logger logger = LoggerFactory.getLogger(CachedWundergroundClient.class);

	/**
	 * Wunderground service API key
	 */
	@Value("${service.api.key}")
	private String serviceApiKey;

	/**
	 * Wunderground service URL template
	 */
	@Value("${service.url}")
	private String serviceUrl;

	/**
	 * Spring Rest template
	 */
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WundergroundResponseMapper mapper;

	@Override
	@Cacheable("cityWeather")
	public CityWeatherDTO getCityWeather(String state, String city) {
		String msg = String.format("Cache does not contain city %s %s", state, city);
		logger.warn(msg);
		return callCityWeather(state, city);
	}

	@Override
	@CachePut("cityWeather")
	public CityWeatherDTO updateCityWeather(String state, String city) {
		CityWeatherDTO ret;
		ret = callCityWeather(state, city);
		return ret;
	}

	/**
	 * Calls wunderground web service for city weather
	 * 
	 * @param state
	 *            - name of state - case sensitive
	 * @param city
	 *            - name of city case insensitive
	 * @return weather for city which is cached
	 * @return
	 */
	private CityWeatherDTO callCityWeather(String state, String city) {
		logger.debug(String.format("Call Wunderground for: %s %s ", state, city));
		CityWeatherDTO result;
		try {
			Response wundergroundResponse = restTemplate.getForObject(serviceUrl, Response.class, serviceApiKey, state, city);
			result = mapper.mapWundergroundResponse(wundergroundResponse, new CityWeatherDTO());
		} catch (Exception ex) {
			throw new ServerException(ErrorCodes.WUNDERGROUND_CALL_FAILED, "Call Wunderground FAILED!", ex);
		}
		logger.debug(String.format("Successfully fetched data from Wunderground: %s %s", state, city));
		return result;
	}
}
