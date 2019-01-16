package com.yin.yzjcourse.DesignModel.P5单例模式.P5_2线程安全的单例1同步锁;

public class Singleton {
	private static Singleton uniqueInstance;
 
	// other useful instance variables here
 
	private Singleton() {}

	//每次获取单例都要同步，大大降低性能。不好，并且只有第一次获取单例时同步才有效，uniqueInstance不为null以后
    //同步不再有意义又拖累性能。
    //如果getInstance()不会频繁 调用，或者不在意这些性能开销，可以采用这种方式
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
	public String getDescription() {
		return "I'm a thread safe Singleton!";
	}
}
