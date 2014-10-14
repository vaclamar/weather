package com.tieto.homework.weather.client;

import com.tieto.homework.weather.dto.CityWeatherDTO;

public interface ICacheWeatherClient {
	CityWeatherDTO updateCityWeather(String state,String city);
	CityWeatherDTO getCityWeather(String state,String city);
}
