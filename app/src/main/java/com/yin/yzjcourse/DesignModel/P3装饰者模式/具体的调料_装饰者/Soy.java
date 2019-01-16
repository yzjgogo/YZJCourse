package com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的调料_装饰者;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.调料基类_需继承饮料基类.CondimentDecorator;

public class Soy extends CondimentDecorator {
	Beverage beverage;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	public double cost() {
		return .15 + beverage.cost();
	}
}
