package com.yin.yzjcourse.DesignModel.P4_1简单工厂模式;

import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.创建披萨的工厂.SimplePizzaFactory;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.工厂的客户_披萨店.PizzaStore;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.抽象的披萨.Pizza;

/**
 * 工厂模式用来封装对象的创建
 * 简单工厂模式：简单工厂模式并不是一个设计模式，更像是一个编程习惯，只是把对象的创建代码抽离出来。
 * 参考：simple_factory_img.png
 */
public class SimpleFactoryTest {
 
	public static void main(String[] args) {
		//披萨店需要一个工厂创建生披萨
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore store = new PizzaStore(factory);

		Pizza pizza = store.orderPizza("cheese");
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
 
		pizza = store.orderPizza("veggie");
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
	}
}
