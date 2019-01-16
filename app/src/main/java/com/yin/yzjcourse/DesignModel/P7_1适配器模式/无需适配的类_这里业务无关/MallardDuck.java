package com.yin.yzjcourse.DesignModel.P7_1适配器模式.无需适配的类_这里业务无关;

import com.yin.yzjcourse.DesignModel.P7_1适配器模式.目标接口.Duck;

public class MallardDuck implements Duck {
	public void quack() {
		System.out.println("Quack");
	}
 
	public void fly() {
		System.out.println("I'm flying");
	}
}
