package com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.抽象的状态;

/**
 * 定义一个状态接口，所有的具体状态都要实现这个接口。
 * 因为对于一个糖果机，无论处于什么状态下，顾客都有可能做各种操作，所以，任何状态下都有全部的方法，因此把这些方法定义在该接口里。
 * 这些方法在不同的具体状态下会做出相应的处理。
 */
public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
	
	public void refill();
}
