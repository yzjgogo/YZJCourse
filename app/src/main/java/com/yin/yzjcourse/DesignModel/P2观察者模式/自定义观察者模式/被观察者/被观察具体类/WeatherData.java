package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察具体类;

import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者基类.Observer;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察基类.Subject;

import java.util.*;

public class WeatherData implements Subject {
	//所有的观察者用一个列表来维护
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		observers = new ArrayList<Observer>();
	}

	//观察者注册就是想列表 中增加一个观察者
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	//取消注册就是从列表移除观察者
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	//通知所有观察者，就是遍历列表依次调用观察者超类的update()
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(temperature, humidity, pressure);
		}
	}
	
	public void measurementsChanged() {
		notifyObservers();
	}

	//模拟被观察者的数据更新
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		//被观察者有更新立马通知所有观察者
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
