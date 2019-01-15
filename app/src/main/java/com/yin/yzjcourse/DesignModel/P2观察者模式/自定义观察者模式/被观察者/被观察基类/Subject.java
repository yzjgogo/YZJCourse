package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察基类;

import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者基类.Observer;

/**
 * 被观察者基类，定义了注册、取消注册、通知的方法
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
