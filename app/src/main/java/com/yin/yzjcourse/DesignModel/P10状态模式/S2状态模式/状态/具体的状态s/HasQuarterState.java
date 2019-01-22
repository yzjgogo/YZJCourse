package com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s;

//import java.util.Random;

import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.业务类_使用状态.GumballMachine;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.抽象的状态.State;

public class HasQuarterState implements State {
	GumballMachine gumballMachine;
 
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
  
	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}
 
	public void ejectQuarter() {
		System.out.println("Quarter returned");
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}
 
	public void turnCrank() {
		System.out.println("You turned...");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

    public void dispense() {
        System.out.println("No gumball dispensed");
    }
    
    public void refill() { }
 
	public String toString() {
		return "waiting for turn of crank";
	}
}
