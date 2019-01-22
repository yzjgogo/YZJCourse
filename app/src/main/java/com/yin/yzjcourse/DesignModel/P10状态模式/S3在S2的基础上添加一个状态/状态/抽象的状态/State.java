package com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.状态.抽象的状态;

public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
	
	public void refill();
}
