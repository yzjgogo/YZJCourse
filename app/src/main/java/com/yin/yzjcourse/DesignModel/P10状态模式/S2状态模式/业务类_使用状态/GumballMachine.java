package com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.业务类_使用状态;

import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s.HasQuarterState;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s.NoQuarterState;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s.SoldOutState;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.具体的状态s.SoldState;
import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.状态.抽象的状态.State;

/**
 * 业务类Context
 * 涉及到各种状态，但是这些状态都属于State类型。
 * 虽然一个糖果机可以有多个状态，但是在任意时刻，该糖果机只有一个状态，也就是state属性
 *
 * 这里用到了组合，将状态组合到该类中，将该类的行为委托给状态类处理，更具体的说给当前状态state处理。
 */
public class GumballMachine {
 
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
 
	State state;//糖果机的当前状态，可变的。
	int count = 0;//糖果机当前的糖果数量

    /**
     * 创建糖果机时，初始化各种状态、当前状态、当前糖果数。
     * @param numberGumballs
     */
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);

		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			state = noQuarterState;
		} else {
			state = soldOutState;
		}
	}

	/**
     * 随着state的改变，该方法也会执行相应状态的逻辑，也就是方法会随着状态的改变而改变。
     * 也就说当前类的动作已经有各个具体的状态自己去处理了，使用’组合‘改变了类的行为。
	 */
	public void insertQuarter() {
		state.insertQuarter();
	}

    /**
     * 随着state的改变，该方法也会执行相应状态的逻辑，也就是方法会随着状态的改变而改变。
     */
	public void ejectQuarter() {
		state.ejectQuarter();
	}

    /**
     * 随着state的改变，该方法也会执行相应状态的逻辑，也就是方法会随着状态的改变而改变。
     */
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}
 
	public void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (count != 0) {
			count = count - 1;
		}
	}
 
	public int getCount() {
		return count;
	}
 
	public void refill(int count) {
		this.count += count;
		System.out.println("The gumball machine was just refilled; it's new count is: " + this.count);
		state.refill();
	}

	public void setState(State state) {
		this.state = state;
	}
    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }
 
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004");
		result.append("\nInventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + state + "\n");
		return result.toString();
	}
}
