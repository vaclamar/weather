package com.tieto.homework.weather.soap.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.tieto.homework.weather.CityWeatherType;
import com.tieto.homework.weather.dto.CityWeatherDTO;

@Component
public class CityWeatherTypeMapper {
	public CityWeatherType mapCityWeatherType(CityWeatherType target, CityWeatherDTO source) {
		target.setLocation(source.getLocation());
		target.setRelativeHumidity(source.getRelativeHumidity());
		target.setTempC(new BigDecimal(source.getTemperatureCelsius()));
		target.setWeather(source.getWeather());
		target.setWindDir(source.getWindDirection());
		target.setWindString(source.getWindString());
		return target;
	}
}
