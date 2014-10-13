package com.tieto.homework.weather;

import com.tieto.homework.weather.dto.CityWeatherDTO;

public interface IWeatherService {

	String test();
	
	/**
	 * Method used for getting weather data.
	 * 
	 * @param city City for weather data fetch.
	 * @return Weather data for city from request.
	 * @throws ServerError
	 */
	CityWeatherDTO getWeatherData(String city);
}
