package com.yin.yzjcourse.DesignModel.P1策略模式.飞行行为.具体的飞行类s;

import com.yin.yzjcourse.DesignModel.P1策略模式.飞行行为.飞行基类.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying!!");
	}
}
