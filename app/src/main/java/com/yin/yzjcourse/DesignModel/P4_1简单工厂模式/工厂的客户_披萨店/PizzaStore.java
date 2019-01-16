package com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.工厂的客户_披萨店;

import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.抽象的披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.创建披萨的工厂.SimplePizzaFactory;

public class PizzaStore {
	SimplePizzaFactory factory;
 
	public PizzaStore(SimplePizzaFactory factory) { 
		this.factory = factory;
	}
 
	public Pizza orderPizza(String type) {
		Pizza pizza;
 		//创建生披萨的工作交给工厂，我只要披萨，具体是哪种披萨，我不管，我以抽象对象Pizza接收
		//如果在披萨店创建披萨，机会紧耦合关联各种具体类型的披萨，不利于维护。这样实现生披萨的生产和加工分离。
		pizza = factory.createPizza(type);

		//任何类型的披萨都需要经过下面几步加工，所以我无需知道具体的披萨类型。
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

}
