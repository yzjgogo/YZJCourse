package com.yin.yzjcourse.DesignModel.P1策略模式.叫唤行为.具体的叫唤类s;

import com.yin.yzjcourse.DesignModel.P1策略模式.叫唤行为.叫唤基类.QuackBehavior;

public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
