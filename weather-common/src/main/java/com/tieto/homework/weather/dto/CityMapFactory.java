package com.tieto.homework.weather.dto;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * Factory for creating city Map
 * 
 * @author vaclbmar
 *
 */
@Component
public class CityMapFactory {

	/**
	 * Map of supported cities. Key is city name and value is city state.
	 */
	@Resource(name = "cityMap")
	private Map<String, String> cityMap;

	/**
	 * Gets city map
	 * 
	 * @return city map
	 */
	public Map<String, String> getCityMap() {
		return cityMap;
	}
}
