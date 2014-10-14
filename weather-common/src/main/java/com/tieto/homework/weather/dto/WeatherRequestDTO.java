package com.tieto.homework.weather.dto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Value object for storing request.
 */
public class WeatherRequestDTO {
	
	private Collection<String> cities;

	public WeatherRequestDTO() {
		this.cities = new ArrayList<String>();
	}	
	
	public Collection<String> getCities() {
		return cities;
	}
}
