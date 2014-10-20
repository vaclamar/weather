package com.tieto.homework.weather.client;

import com.tieto.homework.weather.dto.CityWeatherDTO;

/**
 * Client for weather service. Weather service calls are cached
 * 
 * @author vaclbmar
 */
public interface ICacheWeatherClient {
	/**
	 * Updates cache for city from weather service
	 * 
	 * @param state
	 *            - name of state - case sensitive
	 * @param city
	 *            - name of city case insensitive
	 * @return weather for city which is cached
	 */
	CityWeatherDTO updateCityWeather(String state, String city);

	/**
	 * Gets city weather for city. If value doesn't exist in the cache, web
	 * service would be called.
	 * 
	 * @param state
	 *            - name of state - case sensitive
	 * @param city
	 *            - name of city case insensitive
	 * @return weather for city
	 */
	CityWeatherDTO getCityWeather(String state, String city);
}
