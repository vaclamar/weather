package com.tieto.homework.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tieto.homework.weather.IWeatherService;
import com.tieto.homework.weather.dto.CityWeatherDTO;

@Controller
public class WeatherRESTController {

	@Autowired
	private IWeatherService service;
	
	@RequestMapping(value="/simpleTest", method=RequestMethod.GET)
	@ResponseBody
	public String simpleTest() {		
		return "REST simple test";
	}
	
//	@RequestMapping(value="/test", method=RequestMethod.GET)
//	@ResponseBody
//	public String test() {		
//		return service.test();
//	}
	
	@RequestMapping(value="/weather/{city}", method=RequestMethod.GET)
	@ResponseBody	
	public CityWeatherDTO getWeatherData(@PathVariable("city") String city) {
		CityWeatherDTO ret = service.getWeatherData(city);
		return ret;
	}
}
