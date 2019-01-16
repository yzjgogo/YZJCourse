package com.yin.yzjcourse.DesignModel.P5单例模式.P5_1经典的单例;

/**
 * 非线程安全的
 */

public class Singleton {
	private static Singleton uniqueInstance;

	//私有构造方法
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
		    //多个线程都进入到if里面，就创建多次了。
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
	public String getDescription() {
		return "I'm a classic Singleton!";
	}
}
