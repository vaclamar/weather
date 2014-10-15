package com.tieto.homework.weather.soap.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tieto.homework.weather.CityWeatherType;
import com.tieto.homework.weather.WeatherResponse;
import com.tieto.homework.weather.dto.CityWeatherDTO;

@Component
public class WeatherResponseMapper {
	public WeatherResponse mapWeatherResponse(List<CityWeatherDTO> source, WeatherResponse response) {
		
		for (CityWeatherDTO weather : source) {
			CityWeatherType item = new CityWeatherType();
			item.setLocation(weather.getLocation());
			item.setRelativeHumidity(weather.getRelativeHumidity());
			item.setTempC(BigDecimal.valueOf(weather.getTemperatureCelsius()));
			item.setWeather(weather.getWeather());
			item.setWindDir(weather.getWindDirection());
			item.setWindString(weather.getWindString());
			
//			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(weather.getWeatherDate());
			item.setObservationTime(weather.getWeatherDate());
			response.getCityWeather().add(item);
		}
		
		return response; 
	}
}
