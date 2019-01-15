package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s;

import com.yin.yzjcourse.DesignModel.P2观察者模式.额外的业务接口.DisplayElement;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察具体类.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者基类.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
		tempSum += temp;
		numReadings++;

		if (temp > maxTemp) {
			maxTemp = temp;
		}
 
		if (temp < minTemp) {
			minTemp = temp;
		}

		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}
}
