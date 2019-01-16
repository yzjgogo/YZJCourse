package com.yin.yzjcourse.DesignModel.P5单例模式.P5_3线程安全的单例2非延迟创建;

public class TestSafe2 {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.getDescription());
	}
}
