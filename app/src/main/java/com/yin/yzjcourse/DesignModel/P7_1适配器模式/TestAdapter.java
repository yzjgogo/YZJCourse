package com.yin.yzjcourse.DesignModel.P7_1适配器模式;

import com.yin.yzjcourse.DesignModel.P7_1适配器模式.无需适配的类_这里业务无关.MallardDuck;
import com.yin.yzjcourse.DesignModel.P7_1适配器模式.目标接口.Duck;
import com.yin.yzjcourse.DesignModel.P7_1适配器模式.被适配者.具体被适配者.WildTurkey;
import com.yin.yzjcourse.DesignModel.P7_1适配器模式.适配器.TurkeyAdapter;

/**
 * 适配器模式：
 * 将一个类的接口，转换成客户期望的另一个接口。适配器让原本不兼容的类可以合作无间。
 * 在TurkeyAdapter中将火鸡的接口Turkey转换成鸭子接口Duck，这样客户端clinet()
 * 原本与Turkey不兼容，现在就兼容了，切无需改变client()的任何代码。
 *
 * 适配器模式可以将任意接口(你现在想使用的新接口)间接替换成目标接口(已经在使用的接口)。
 *
 * 设计原则：
 * 封装变化：这里的变化是不同的被适配者有不同的行为，通过用适配器实现目标接口，由目标接口的方法去调用被适配者的方法，将变化封装起来，
 *          这样无需修改目标接口类，也无需修改被适配者。
 * 多用组合，少用继承：这里被适配者和适配器是以组合的方式实现的。并不是让一个火鸡直接实现Duck接口，这样更灵活。
 * 针对接口编程，不针对实现编程：这里客户端client()关注的是目标接口，不关注具体类；适配器也是实现目标接口。
 * 为交互对象之间的松耦合设计而努力：client()和被适配者是完全松耦合的，client()甚至不知道Turkey的存在。
 * 类应该对扩展开放，对修改关闭：任何接口想要实目标接口的功能，只需写一个适配器将该接口 作为被适配者就可实现扩展client()的功能。
 *                          也就是client()原本只能执行目标接口的方法，现在可以间接执行任意接口的方法了。
 * 依赖抽象，不依赖具体实现：client()就依赖接口Duck，适配器的被适配者也是接口类型，这样适配器可以适配被适配接口的任意子类。
 *
 * 参考图片:adapter_p_1.png
 */
public class TestAdapter {
	public static void main(String[] args) {
	    //鸭子
		MallardDuck duck = new MallardDuck();

		//火鸡
		WildTurkey turkey = new WildTurkey();
        //将turkey适配成鸭子，假鸭子
		Duck turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe Duck says...");
		client(duck);

		System.out.println("\nThe TurkeyAdapter says...");
		client(turkeyAdapter);
	}

    //客户只需要目标接口Duck，因此当我希望调用火鸡时，就将火鸡适配成鸭子。
	static void client(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
