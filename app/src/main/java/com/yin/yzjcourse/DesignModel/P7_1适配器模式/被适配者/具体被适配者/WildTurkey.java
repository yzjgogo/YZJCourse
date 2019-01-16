package com.yin.yzjcourse.DesignModel.P7_1适配器模式.被适配者.具体被适配者;

import com.yin.yzjcourse.DesignModel.P7_1适配器模式.被适配者.抽象被适配者.Turkey;

public class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}
 
	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}
