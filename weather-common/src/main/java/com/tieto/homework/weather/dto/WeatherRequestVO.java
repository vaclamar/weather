package com.tieto.homework.weather.dto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Value object for storing request.
 */
public class WeatherRequestVO {
	
	private Collection<String> cities;

	public WeatherRequestVO() {
		this.cities = new ArrayList<String>();
	}	
	
	public Collection<String> getCities() {
		return cities;
	}
}
