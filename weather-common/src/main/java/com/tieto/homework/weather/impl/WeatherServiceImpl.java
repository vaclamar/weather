package com.tieto.homework.weather.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.client.ICacheWeatherClient;
import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.weather.dto.CityMapFactory;
import com.tieto.homework.weather.exception.ClientException;
import com.tieto.homework.weather.exception.ErrorCodes;

@Component
public class WeatherServiceImpl implements IWeatherService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Autowired	
	private CityMapFactory cityMapFactory;
	
	@Autowired
	private ICacheWeatherClient weatherClient;

	@Override
	public CityWeatherDTO getWeatherData(String city) {
		if(cityMapFactory.getCityMap().containsKey(city.toLowerCase())) {
			String state = cityMapFactory.getCityMap().get(city.toLowerCase());
			return weatherClient.getCityWeather(state, city.toLowerCase());
		} else {
			throw new ClientException(ErrorCodes.NOT_SUPPORTED_CITY, String.format("Unsupported city %s", city));
		}
	}

	@Override
	public List<CityWeatherDTO> getAllWeatherData() {
		List<CityWeatherDTO> ret = new ArrayList<CityWeatherDTO>();
		for(Entry<String, String> city : cityMapFactory.getCityMap().entrySet()) {
			logger.debug(String.format("Call updateCityWeather for: %s %s ",city.getKey(), city.getValue()));
			CityWeatherDTO weather = weatherClient.getCityWeather(city.getValue(), city.getKey());
			ret.add(weather);
		}
		return ret;
	}
	
	
	
}
