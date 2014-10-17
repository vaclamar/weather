package com.tieto.homework.weather.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.dto.CityMapFactory;
import com.tieto.homework.weather.dto.CityWeatherDTO;
import com.tieto.homework.weather.exception.ClientException;
import com.tieto.homework.wunderground.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:META-INF/common-config.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class WeatherServiceTest {

    @Autowired
    private IWeatherService service;
    
    /**
     * we don't need it for this test
     */
    @Autowired
    @ReplaceWithMock	
    private Jaxb2Marshaller xmlMarshaller;
    
    /**
     * This is mocked
     */
    @Autowired
    @ReplaceWithMock
	private RestTemplate restTemplate;
    
    /**
     * This is mocked
     */
    @Autowired
    @ReplaceWithMock
	private CityMapFactory cityMapFactory;
    
    private String URI;
    private String apikey;
	
	@Before
	public void setUp() throws Exception {
	   	HashMap<String, String> citiesMap = new HashMap<String, String>();
    	citiesMap.put("ostrava", "Czech");
    	citiesMap.put("rome", "Italy"); 
		
    	URI = "http://api.wunderground.com/api/{service.api.key}/conditions/q/{country}/{city}.xml";
    	apikey = "a416203bf3090107";
    	
    	Response response = mock(Response.class, RETURNS_DEEP_STUBS);
    	
    	when(response.getCurrentObservation().getDisplayLocation().getFull()).thenReturn("Ostrava");
    	when(response.getCurrentObservation().getRelativeHumidity()).thenReturn("40%");
    	when(response.getCurrentObservation().getTempC()).thenReturn("21");
    	when(response.getCurrentObservation().getWeather()).thenReturn("Clear");
    	when(response.getCurrentObservation().getWindDir()).thenReturn("NNW");
    	when(response.getCurrentObservation().getWindString()).thenReturn("Calm");
    	
    	when(restTemplate.getForObject(URI, Response.class, apikey, "Czech", "ostrava")).thenReturn(response);
    	
    	Response response2 = mock(Response.class, RETURNS_DEEP_STUBS);
    	
    	when(response2.getCurrentObservation().getDisplayLocation().getFull()).thenReturn("Rome");
    	when(response2.getCurrentObservation().getRelativeHumidity()).thenReturn("40%");
    	when(response2.getCurrentObservation().getTempC()).thenReturn("21");
    	when(response2.getCurrentObservation().getWeather()).thenReturn("Clear");
    	when(response2.getCurrentObservation().getWindDir()).thenReturn("NNW");
    	when(response2.getCurrentObservation().getWindString()).thenReturn("Calm");
    	
    	when(restTemplate.getForObject(URI, Response.class, apikey, "Italy", "rome")).thenReturn(response2);
    	
    	when(cityMapFactory.getCityMap()).thenReturn(citiesMap);
	}

	@Test
	public void testGetWeatherData() {
    	String city = "rome";
    	CityWeatherDTO weatherData = service.getWeatherData(city);
    	assertEquals("Rome", weatherData.getLocation());
    	assertEquals("40%", weatherData.getRelativeHumidity());
    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
    	assertEquals("Clear", weatherData.getWeather());
    	assertEquals("NNW", weatherData.getWindDirection());
    	assertEquals("Calm", weatherData.getWindString());
	}
	
    @Test(expected = ClientException.class)
	public void testGetWeatherDataUnsupported() {
    	String city = "wien";
    	service.getWeatherData(city);    	
	}
	
	@Test
	public void testGetAllWeatherData() {
    	List<CityWeatherDTO> weatherDataList = service.getAllWeatherData();
    	assertEquals(2, weatherDataList.size());
    	for(CityWeatherDTO weatherData : weatherDataList) {
	    	assertEquals("40%", weatherData.getRelativeHumidity());
	    	assertEquals(Double.valueOf(21), weatherData.getTemperatureCelsius());
	    	assertEquals("Clear", weatherData.getWeather());
	    	assertEquals("NNW", weatherData.getWindDirection());
	    	assertEquals("Calm", weatherData.getWindString());
    	}
	}
}
