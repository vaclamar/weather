package com.tieto.homework.weather;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tieto.homework.weather.client.ICacheWeatherClient;

@Component
public class UpdateCacheJob {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateCacheJob.class);
	
	@Resource(name="cityMap")
	private Map<String, String> cityMap;
	
	@Autowired
	private ICacheWeatherClient clientCache;
	
	@Scheduled(initialDelay= 100, fixedDelayString="${service.cache.time.ms}" )
	//@Scheduled(fixedDelay=7200000)
	//@Scheduled(fixedDelay=7200)
	public void updateWundergroundCache() {		
		for(Entry<String, String> city : cityMap.entrySet()) {
			logger.debug(String.format("Call updateCityWeather for: %s %s ",city.getKey(), city.getValue()));
			clientCache.updateCityWeather(city.getValue(), city.getKey());
		}
	}
}
