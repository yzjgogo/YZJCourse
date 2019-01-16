package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.抽象披萨店_工厂方法模式;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;

public abstract class PizzaStore {

    //不创建生披萨，由子类去创建，我只接收抽象披萨对象，子类决定创建哪种具体的披萨
	public abstract Pizza createPizza(String item);
 
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
