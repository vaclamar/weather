package com.tieto.homework.weather.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.tieto.homework.weather.client.ICacheWeatherClient;
import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.wunderground.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:common-test-config.xml"})
public class CachedWundergroundClientTest {

    @Autowired
    private ICacheWeatherClient cacheClient;
    
    @Autowired
    @ReplaceWithMock
	private RestTemplate restTemplate;
    private String URI;
    private String apikey;
	
	@Before
	public void setUp() throws Exception {
	   	HashMap<String, String> citiesMap = new HashMap<String, String>();
    	citiesMap.put("Ostrava", "Czech");
    	citiesMap.put("Oslo", "Norway");    		   
    	URI = "http://api.wunderground.com/api/{service.api.key}/conditions/q/{country}/{city}.xml";
    	apikey = "a416203bf3090107";
    	
    	Response response = mock(Response.class, RETURNS_DEEP_STUBS);
    	
    	when(response.getCurrentObservation().getDisplayLocation().getFull()).thenReturn("Ostrava");
    	when(response.getCurrentObservation().getRelativeHumidity()).thenReturn("40%");
    	when(response.getCurrentObservation().getTempC()).thenReturn("21");
    	when(response.getCurrentObservation().getWeather()).thenReturn("Clear");
    	when(response.getCurrentObservation().getWindDir()).thenReturn("NNW");
    	when(response.getCurrentObservation().getWindString()).thenReturn("Calm");
    	
    	when(restTemplate.getForObject(URI, Response.class, apikey, "Czech", "Ostrava")).thenReturn(response);		
	}

	@Test
	public void testGetWeatherData() {
    	
		String state = "Czech";
    	String city = "Ostrava";
    	CityWeatherDTO weatherData = cacheClient.getCityWeather(state, city);
    	
    	assertEquals("Ostrava", weatherData.getLocation());
    	assertEquals("40%", weatherData.getRelativeHumidity());
    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
    	assertEquals("Clear", weatherData.getWeather());
    	assertEquals("NNW", weatherData.getWindDirection());
    	assertEquals("Calm", weatherData.getWindString());
	}

	@Test
	public void testUpdateCityWeather() {
		String state = "Czech";
    	String city = "Ostrava";
    	CityWeatherDTO weatherData = cacheClient.getCityWeather(state, city);
    	
    	assertEquals("Ostrava", weatherData.getLocation());
    	assertEquals("40%", weatherData.getRelativeHumidity());
    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
    	assertEquals("Clear", weatherData.getWeather());
    	assertEquals("NNW", weatherData.getWindDirection());
    	assertEquals("Calm", weatherData.getWindString());
	}

}
