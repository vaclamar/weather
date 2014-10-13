package com.tieto.homework.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherWSController {

	@RequestMapping(value="/testee", method=RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "test Sluzby";
	}
	
}
