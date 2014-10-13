package com.tieto.homework.weather.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Value object for storing cities from properties.
 */
public class CitiesDTOFactory {
	
	
	private Map<String, String> cities;
	

	public CitiesDTOFactory(String configCities) {

		this.cities = new HashMap<String, String>();
		
		Scanner countryScaner = new Scanner(configCities).useDelimiter(";");
		while (countryScaner.hasNext()) {

			String countryBlock = countryScaner.next();
			Scanner cityScaner = new Scanner(countryBlock).useDelimiter("\\w*:|,");
			String country = cityScaner.findInLine("\\w*:").replace(":", "");
			while (cityScaner.hasNext()) {
				String city = cityScaner.next();
				this.cities.put(city, country);
			}
			cityScaner.close();
		}
		countryScaner.close();
	}

	public Map<String, String> getCities() {
		return this.cities;
	}
}
