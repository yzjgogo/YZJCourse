package com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的饮料_被装饰者;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;

public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
	}
 
	public double cost() {
		return .89;
	}
}

