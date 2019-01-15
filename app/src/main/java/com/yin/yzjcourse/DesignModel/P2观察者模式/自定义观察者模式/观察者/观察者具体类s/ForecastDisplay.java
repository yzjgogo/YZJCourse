package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s;

import com.yin.yzjcourse.DesignModel.P2观察者模式.额外的业务接口.DisplayElement;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察具体类.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者基类.Observer;

public class ForecastDisplay implements Observer, DisplayElement {
	private float currentPressure = 29.92f;  
	private float lastPressure;
	private WeatherData weatherData;

	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;

		display();
	}

	public void display() {
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}
}
