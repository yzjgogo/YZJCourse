package com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式;

import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.被观察者.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.观察者.CurrentConditionsDisplay;
import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.观察者.ForecastDisplay;
import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.观察者.StatisticsDisplay;

public class TestInner {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
