package com.tieto.homework.weather;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tieto.homework.weather.client.ICacheWeatherClient;
import com.tieto.homework.weather.dto.CityMapFactory;

/**
 * Task for scheduler. The task job is update cache.
 * 
 * @author vaclbmar
 */
@Component
public class UpdateCacheJob {

	private static final Logger logger = LoggerFactory.getLogger(UpdateCacheJob.class);

	@Autowired
	private CityMapFactory cityMapFactory;

	/**
	 * Cached client of main weather service.
	 */
	@Autowired
	private ICacheWeatherClient clientCache;

	/**
	 * Fills cache by supported cities.
	 */
	@Scheduled(initialDelay = 100, fixedDelayString = "${service.cache.time.ms}")
	public void updateWundergroundCache() {
		for (Entry<String, String> city : cityMapFactory.getCityMap().entrySet()) {
			logger.debug(String.format("Call updateCityWeather for: %s %s ", city.getKey(), city.getValue()));
			clientCache.updateCityWeather(city.getValue(), city.getKey());
		}
	}
}
