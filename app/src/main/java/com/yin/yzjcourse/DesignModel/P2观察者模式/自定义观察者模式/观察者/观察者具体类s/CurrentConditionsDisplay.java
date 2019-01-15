package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s;

import com.yin.yzjcourse.DesignModel.P2观察者模式.额外的业务接口.DisplayElement;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察基类.Subject;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者基类.Observer;


public class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
