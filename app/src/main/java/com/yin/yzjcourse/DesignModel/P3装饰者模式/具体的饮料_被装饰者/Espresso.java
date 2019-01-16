package com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的饮料_被装饰者;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;

public class Espresso extends Beverage {
  
	public Espresso() {
		description = "Espresso";
	}
  
	public double cost() {
		return 1.99;
	}
}

