package com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.状态.具体的状态s;

import com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.业务类_使用状态.GumballMachine;
import com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.状态.抽象的状态.State;

public class NoQuarterState implements State {
    GumballMachine gumballMachine;
 
    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
 
	public void insertQuarter() {
		System.out.println("You inserted a quarter");
		gumballMachine.setState(gumballMachine.getHasQuarterState());
	}
 
	public void ejectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there's no quarter");
	 }
 
	public void dispense() {
		System.out.println("You need to pay first");
	} 
	
	public void refill() { }
 
	public String toString() {
		return "waiting for quarter";
	}
}
