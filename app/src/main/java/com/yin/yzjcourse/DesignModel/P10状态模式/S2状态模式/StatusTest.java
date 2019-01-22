package com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式;

import com.yin.yzjcourse.DesignModel.P10状态模式.S2状态模式.业务类_使用状态.GumballMachine;

/**
 * 状态模式：
 * 允许对象在内部状态改变时改变它的行为，对象看起来好像修改了它的类。
 *
 * 注意点：
 * 0：每一个状态都实现了该状态下具体的行为(方法)；
 * 1：以组合的方式将状态组合进业务类中；
 * 2：业务类的行为实现实际上是委托给状态去实现的；
 *
 *
 *  设计原则：
 *  封装变化：将易变的状态封装起来；
 *  多用组合，少用集成：状态组合到业务类中。将类的应为通过状态代理实现。
 *  针对接口编程，不针对实现编程：各个状态都有实现共同的接口。
 *  为交互对象之间的松耦合设计而努力：各个状态是解耦的，业务类和具体的状态是解耦的。
 *  类应该对扩展开放，对修改关闭(开闭原则)：想新增状态只需新增一个类实现状态接口即可。
 *  依赖抽象，不依赖具体类(依赖倒置原则)：业务类依赖抽象的状态接口Status，不关心具体的状态。
 *  只和朋友交谈(最少知识原则)：只调用了自己属性的方法，(状态的方法)
 *  别找我，我会找你(好莱坞原则)：客户代码单项调用代理类，代理类单项调用处理器Handler.
 *  类应该只有一个改变的理由(单一职责原则)：各个状态都是单一职责。
 *
 * 状态模式类图：status_p_1.png
 */
public class StatusTest {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(2);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		gumballMachine.refill(5);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
