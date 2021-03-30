package com.toastedbits.headfirst.chapter2.weather;

public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatDisplay = new HeatIndexDisplay(weatherData);
		
		weatherData.setMeasurements(80f, 65f, 30.4f);
		weatherData.setMeasurements(82f, 70f, 29.2f);
		weatherData.setMeasurements(78f, 90f, 29.2f);
	}
}
