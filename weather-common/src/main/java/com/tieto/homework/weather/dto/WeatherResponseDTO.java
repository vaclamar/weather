package com.tieto.homework.weather.dto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DTO for storing response.
 */
public class WeatherResponseDTO {

	private Collection<CityWeatherDTO> cityWeather;
	
	public WeatherResponseDTO() {
		this.cityWeather = new ArrayList<CityWeatherDTO>();
	}
	
	public WeatherResponseDTO(CityWeatherDTO city) {
		this.cityWeather = new ArrayList<CityWeatherDTO>();
		this.cityWeather.add(city);
	}

	public Collection<CityWeatherDTO> getCityWeather() {
		return cityWeather;
	}
	
}
