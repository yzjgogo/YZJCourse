package com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s;

import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.业务类_使用状态.GumballMachine;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.抽象的状态.State;

public class SoldOutState implements State {
    GumballMachine gumballMachine;
 
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
 
	public void insertQuarter() {
		System.out.println("You can't insert a quarter, the machine is sold out");
	}
 
	public void ejectQuarter() {
		System.out.println("You can't eject, you haven't inserted a quarter yet");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there are no gumballs");
	}
 
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
	
	public void refill() { 
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}
 
	public String toString() {
		return "sold out";
	}
}
