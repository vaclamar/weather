package com.tieto.homework.weather.mapper;

import org.springframework.stereotype.Component;

import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.wunderground.Response;

/**
 * Mapping from Wunderground response to service DTO
 * @author vaclbmar
 *
 */
@Component
public class WundergroundResponseMapper {
	/**
	 * Maps from Wunderground response to service DTO
	 * @param source - Wunderground response
	 * @param target - CityWeatherDTO 
	 * @return city weather DTO
	 */
	public CityWeatherDTO mapWundergroundResponse(Response source, CityWeatherDTO target) {

		target.setLocation(source.getCurrentObservation().getDisplayLocation().getFull());
		target.setRelativeHumidity(source.getCurrentObservation().getRelativeHumidity());
		target.setTemperatureCelsius(Double.valueOf(source.getCurrentObservation().getTempC()));
		target.setWeather(source.getCurrentObservation().getWeather());
		target.setWindDirection(source.getCurrentObservation().getWindDir());
		target.setWindString(source.getCurrentObservation().getWindString());
		target.setWeatherDate(source.getCurrentObservation().getObservationTime());

		return target;
	}
}
