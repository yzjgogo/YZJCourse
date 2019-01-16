package com.yin.yzjcourse.DesignModel.P5单例模式.P5_4线程安全的单例3双重检查加锁;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//

public class Singleton {
    //volatile表示某个线程对uniqueInstance赋值后，另一个线程可以立即访问到该值。
	private volatile static Singleton uniqueInstance;
 
	private Singleton() {}

    /**
     * 只有第一次调用getInstance()时才会同步，以后调用直接return即可。
     * 切第一次的时候采用了双重检查加锁，
     * @return
     */
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
		    //两个线程进入到这里后，快的那个线程先继续执行，另一个等待，快的那个可能会创建一个实例后离开，
            //另一个进入synchronized后，还会再判断一次是否为null，如果上一个线程创建了且是volatile，则当前
            //这个线程就可以访问到，就无需 再次创建对象了。
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
