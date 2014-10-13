package com.tieto.homework.weather.impl;

import java.util.Map;



import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.client.IWeatherClient;
import com.tieto.homework.weather.dto.CityWeatherDTO;

@Component
public class WeatherServiceImpl implements IWeatherService {

	@Resource(name="cityMap")
	private Map<String, String> cityMap;
	
	@Autowired
	private IWeatherClient weatherClient;
	
	@Override
	public String test() {		
		return "common service test";
	}

	@Override
	public CityWeatherDTO getWeatherData(String city) {
		if(cityMap.containsKey(city.toLowerCase())) {
			String state = cityMap.get(city.toLowerCase());
			return weatherClient.getCityWeather(state, city);
		} else {
			throw new RuntimeException(String.format("Unsupported city %s", city)); //TODO rebuild to ClientEx;
		}
	}
}
