package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.抽象的披萨店_工厂方法模式在这里;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.抽象的披萨.Pizza;

public abstract class PizzaStore {
    //参照工厂方法模式
	protected abstract Pizza createPizza(String item);
 
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
