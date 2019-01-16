package com.yin.yzjcourse.DesignModel.P5单例模式.P5_4线程安全的单例3双重检查加锁;

public class TestSafe3 {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
	}
}
