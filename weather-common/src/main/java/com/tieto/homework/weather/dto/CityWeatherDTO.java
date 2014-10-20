package com.tieto.homework.weather.dto;

/**
 * DTO for storing city weather informations.
 */
public class CityWeatherDTO {

	private String location;
	private Double temperatureCelsius;
	private String relativeHumidity;
	private String windDirection;
	private String weather;
	private String windString;
	private String weatherDate;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getTemperatureCelsius() {
		return temperatureCelsius;
	}

	public void setTemperatureCelsius(Double temperatureCelsius) {
		this.temperatureCelsius = temperatureCelsius;
	}

	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWindString() {
		return windString;
	}

	public void setWindString(String windString) {
		this.windString = windString;
	}

	public String getWeatherDate() {
		return weatherDate;
	}

	public void setWeatherDate(String weatherDate) {
		this.weatherDate = weatherDate;
	}

}
