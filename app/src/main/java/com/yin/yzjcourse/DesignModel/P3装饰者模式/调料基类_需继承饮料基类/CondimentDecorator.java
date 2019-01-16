package com.yin.yzjcourse.DesignModel.P3装饰者模式.调料基类_需继承饮料基类;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;

public abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
}
