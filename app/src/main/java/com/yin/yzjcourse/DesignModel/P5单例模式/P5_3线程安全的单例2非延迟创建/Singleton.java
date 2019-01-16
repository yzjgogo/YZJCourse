package com.yin.yzjcourse.DesignModel.P5单例模式.P5_3线程安全的单例2非延迟创建;

public class Singleton {
    //直接在类加载时实例化，不延迟实例化，如果创建该对象的负担不重则可采用这种方式。
	private static Singleton uniqueInstance = new Singleton();
 
	private Singleton() {}

	//这种方式是线程安全的，任何线程都可以正确的访问到。
	public static Singleton getInstance() {
		return uniqueInstance;
	}
	
	// other useful methods here
	public String getDescription() {
		return "I'm a statically initialized Singleton!";
	}
}
