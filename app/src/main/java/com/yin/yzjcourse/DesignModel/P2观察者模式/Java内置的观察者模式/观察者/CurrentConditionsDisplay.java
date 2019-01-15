package com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.观察者;

import com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.被观察者.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.额外的业务接口.DisplayElement;

import java.util.Observable;
import java.util.Observer;
/**
 * Observer是java内置的观察者
 * 有update(Observable obs, Object arg)方法，第一个参数是被观察者实例，可通过它取消注册、拉去数据等。
 * 第二个参数是被观察者传递过来的数据。
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
	Observable observable;
	private float temperature;
	private float humidity;
	
	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
