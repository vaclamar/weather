package com.tieto.homework.weather.dto;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CityMapFactory {

	@Resource(name="cityMap")
	private Map<String, String> cityMap;
	
	public Map<String, String> getCityMap() {
		return cityMap;
	}
}
