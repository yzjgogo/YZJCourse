package com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的调料_装饰者;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.调料基类_需继承饮料基类.CondimentDecorator;

/**
 * Mocha是装饰者，beverage是被装饰者；二者具有相同的基类。
 */
public class Mocha extends CondimentDecorator {
	Beverage beverage;//被装饰者
 
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	//可以扩展或替换或修改被装饰者的方法
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	//可以扩展或替换或修改被装饰者的方法
	public double cost() {
		return .20 + beverage.cost();
	}
}
