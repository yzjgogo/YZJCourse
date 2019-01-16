package com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类;

public abstract class Beverage {
	public String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
