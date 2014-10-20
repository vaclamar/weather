package com.tieto.homework.weather;

import java.rmi.ServerError;
import java.util.List;

import com.tieto.homework.weather.dto.CityWeatherDTO;

public interface IWeatherService {
	
	/**
	 * Method used for getting weather data.
	 * 
	 * @param city City for weather data fetch.
	 * @return Weather data for city from request.
	 * @throws ServerError
	 */
	CityWeatherDTO getWeatherData(String city);
	List<CityWeatherDTO> getAllWeatherData();
}
