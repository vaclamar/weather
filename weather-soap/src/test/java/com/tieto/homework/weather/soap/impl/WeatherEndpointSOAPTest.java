package com.tieto.homework.weather.soap.impl;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.dto.CityWeatherDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:META-INF/soap-servlet.xml"})
public class WeatherEndpointSOAPTest {

    @Autowired
    private WeatherEndpointSOAP controller;
    
    /**
     * This is mocked
     */
    @ReplaceWithMock
	@Autowired
	private IWeatherService service;
	
	@Before
	public void setUp() throws Exception {   		   
    	CityWeatherDTO dtoRiga = mock(CityWeatherDTO.class, RETURNS_DEEP_STUBS);
    	
    	List<CityWeatherDTO> cityWeatherDTOList = new ArrayList<CityWeatherDTO>();
    	
    	when(dtoRiga.getLocation()).thenReturn("Riga");
    	when(dtoRiga.getRelativeHumidity()).thenReturn("40%");
    	when(dtoRiga.getTemperatureCelsius()).thenReturn(Double.valueOf("21"));
    	when(dtoRiga.getWeather()).thenReturn("Clear");
    	when(dtoRiga.getWindDirection()).thenReturn("NNW");
    	when(dtoRiga.getWindString()).thenReturn("Calm");
    	cityWeatherDTOList.add(dtoRiga);
    	when(service.getWeatherData("riga")).thenReturn(dtoRiga);
    	
    	CityWeatherDTO dtoHelsinki = mock(CityWeatherDTO.class, RETURNS_DEEP_STUBS);
    	
    	when(dtoHelsinki.getLocation()).thenReturn("Helsinki");
    	when(dtoHelsinki.getRelativeHumidity()).thenReturn("40%");
    	when(dtoHelsinki.getTemperatureCelsius()).thenReturn(Double.valueOf("21"));
    	when(dtoHelsinki.getWeather()).thenReturn("Clear");
    	when(dtoHelsinki.getWindDirection()).thenReturn("NNW");
    	when(dtoHelsinki.getWindString()).thenReturn("Calm");
    	cityWeatherDTOList.add(dtoHelsinki);
    	when(service.getAllWeatherData()).thenReturn(cityWeatherDTOList);
	}

	@Test
	public void testGetWeatherData() {
    	String city = "riga";
    	CityWeatherDTO weatherData = service.getWeatherData(city);
    	
    	assertEquals("Riga", weatherData.getLocation());
    	assertEquals("40%", weatherData.getRelativeHumidity());
    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
    	assertEquals("Clear", weatherData.getWeather());
    	assertEquals("NNW", weatherData.getWindDirection());
    	assertEquals("Calm", weatherData.getWindString());
	}
	
	@Test
	public void testGetWeatherData2() {
    	List<CityWeatherDTO> weatherDataList = service.getAllWeatherData();
    	assertEquals(2, weatherDataList.size());
    	for(CityWeatherDTO weatherData : weatherDataList) {
    		assertThat(weatherData.getLocation(), anyOf(is("Riga"), is("Helsinki")));
	    	assertEquals(weatherData.getLocation(), weatherData.getLocation());
	    	assertEquals("40%", weatherData.getRelativeHumidity());
	    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
	    	assertEquals("Clear", weatherData.getWeather());
	    	assertEquals("NNW", weatherData.getWindDirection());
	    	assertEquals("Calm", weatherData.getWindString());
    	}
	}
}
