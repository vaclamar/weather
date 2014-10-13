package com.tieto.homework.weather.client;

import com.tieto.homework.weather.dto.CityWeatherDTO;

public interface IWeatherClient {
	CityWeatherDTO getCityWeather(String state,String city);
	CityWeatherDTO updateCityWeather(String state,String city);
	void updateWundergroundCache();
}
