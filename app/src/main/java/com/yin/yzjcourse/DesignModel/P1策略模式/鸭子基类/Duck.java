package com.yin.yzjcourse.DesignModel.P1策略模式.鸭子基类;

import com.yin.yzjcourse.DesignModel.P1策略模式.飞行行为.飞行基类.FlyBehavior;
import com.yin.yzjcourse.DesignModel.P1策略模式.叫唤行为.叫唤基类.QuackBehavior;

/**
 * 设计原则：
 * 封装变化：找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。
 * 			这里是，并不是所有的鸭子都具有飞行功能，例如‘木偶鸭子’，也不是所有的鸭子都会叫，例如‘木偶鸭子’；
 *
 * 	针对接口编程，而不是针对实现编程：
 * 			这里是，把应用中变化的部分独立出来后，通过接口来承载这些变化，这样我们只需关心抽象对象，不许关心该抽象对象
 * 			是具体什么类型的了，即只要你有‘飞行’或‘叫唤’的功能即可，具体怎么飞怎么叫我不管。
 *
 * 	多用组合，少用继承：
 * 			‘有一个’可能比‘是一个’更灵活更易于维护，例如类A有一个类B类型的属性，比类A继承了类B更灵活。
 * 			这里是，所有的Duck都有一个FlyBehavior属性和一个QuackBehavior属性，这里就用到组合而非继承。
 *
 *
 *
 *
 * 	策略模式：定义了算法族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。
 * 			这里的算法族是一系列具体的飞行类 和 一系列具体的叫唤类。因为Duck类(客户)是用抽象的FlyBehavior
 * 			和QuackBehavior接收的，所以这些算法族之间可以互相替换。
 * 			Duck类(客户)只要求你具有‘飞行’和‘叫唤’的功能就行了，具体怎么飞怎么叫客户是不管的。
 * 			也就是说我只关心你是不是FlyBehavior和QuackBehavior，而不关心你是具体的哪个。
 */
public abstract class Duck {
	//与接口组合，拥有了接口的功能，也避免了继承。只关心抽象对象，不关系具体对象。可以在Duck的子类创建时赋值。
	public FlyBehavior flyBehavior;
	public QuackBehavior quackBehavior;

	public Duck() {
	}

	//可以灵活改变行为
	public void setFlyBehavior(FlyBehavior fb) {
		flyBehavior = fb;
	}

	public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
	}

	public abstract void display();

	//真正执行飞行的是flyBehavior代理，这样比较灵活，专业的事，交给专业的人。不要想着啥都自己做完。
	public void performFly() {
		flyBehavior.fly();
	}

	public void performQuack() {
		quackBehavior.quack();
	}

	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}
}
