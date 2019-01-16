package com.yin.yzjcourse.DesignModel.P5单例模式.P5_2线程安全的单例1同步锁;

public class TestSafe1 {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.getDescription());
	}
}
