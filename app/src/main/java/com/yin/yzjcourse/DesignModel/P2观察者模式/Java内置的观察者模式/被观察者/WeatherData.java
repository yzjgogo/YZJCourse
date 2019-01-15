package com.yin.yzjcourse.DesignModel.P2观察者模式.Java内置的观察者模式.被观察者;
	
import java.util.Observable;

/**
 * Observable类是java内置的被观察者
 * 内部定义了addObserver(Observer o)、deleteObserver(Observer o)、
 * notifyObservers()、notifyObservers(Object arg)、setChanged()等方法。
 *
 * 其中
 * notifyObservers()需要观察者自己pull数据(在观察者那边调用被观察者的getTemperature()等方法)
 * notifyObservers(Object arg)直接向观察者push数据。
 *
 * setChanged():用于在指定情况下通知观察者，而不是被观察者一有数据变化就立马通知所有观察者，就好像一个
 * 阀门，避免被观察者不断变化时不断的通知观察者，这样你就必须在setChanged()设为true后才能通知观察者,从
 * notifyObservers(Object arg)里可见对changed做了判断。
 */
public class WeatherData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() { }

	/**
	 * 通知所有观察者，只通知，没传递数据，所以需要观察者自己pull数据
	 */
	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
}
