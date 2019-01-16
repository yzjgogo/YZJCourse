package com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s;

import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.抽象的披萨.Pizza;

public class ClamPizza extends Pizza {
	public ClamPizza() {
		name = "Clam Pizza";
		dough = "Thin crust";
		sauce = "White garlic sauce";
		toppings.add("Clams");
		toppings.add("Grated parmesan cheese");
	}
}
