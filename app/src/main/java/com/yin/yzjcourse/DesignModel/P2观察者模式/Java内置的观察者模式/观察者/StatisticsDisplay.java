package com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.观察者;

import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.被观察者.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.额外的业务接口.DisplayElement;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;

	public StatisticsDisplay(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)observable;
			float temp = weatherData.getTemperature();
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
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}
}
