package com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.状态.具体的状态s;

import com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.业务类_使用状态.GumballMachine;
import com.yin.yzjcourse.DesignModel.P10状态模式.S3在S2的基础上添加一个状态.状态.抽象的状态.State;

public class SoldState implements State {
    GumballMachine gumballMachine;
 
    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
       
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}
 
	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}
 
	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball!");
	}
 
	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}
	
	public void refill() { }
 
	public String toString() {
		return "dispensing a gumball";
	}
}
