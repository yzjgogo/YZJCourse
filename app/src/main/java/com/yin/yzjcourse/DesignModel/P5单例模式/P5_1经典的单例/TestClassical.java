package com.yin.yzjcourse.DesignModel.P5单例模式.P5_1经典的单例;

public class TestClassical {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.getDescription());
	}
}
