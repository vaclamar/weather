package com.tieto.homework.weather;

import java.util.List;

import com.tieto.homework.weather.dto.CityWeatherDTO;

/**
 * Weather service java interface
 * 
 * @author vaclbmar
 */
public interface IWeatherService {

	/**
	 * Gets weather data for city
	 * 
	 * @param city
	 *            city name for weather data fetch. Case insensitive.
	 * @return Weather data for city from request.
	 */
	CityWeatherDTO getWeatherData(String city);

	/**
	 * Gets weather data for all supported cities.
	 * 
	 * @return weather data for all supported cities.
	 */
	List<CityWeatherDTO> getAllWeatherData();
}
