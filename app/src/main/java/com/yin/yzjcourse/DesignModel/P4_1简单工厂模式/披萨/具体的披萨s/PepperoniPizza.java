package com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s;

import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.抽象的披萨.Pizza;

public class PepperoniPizza extends Pizza {
	public PepperoniPizza() {
		name = "Pepperoni Pizza";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Sliced Pepperoni");
		toppings.add("Sliced Onion");
		toppings.add("Grated parmesan cheese");
	}
}
