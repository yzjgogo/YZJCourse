package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;

public class NYStyleVeggiePizza extends Pizza {

	public NYStyleVeggiePizza() {
		name = "NY Style Veggie Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
 
		toppings.add("Grated Reggiano Cheese");
		toppings.add("Garlic");
		toppings.add("Onion");
		toppings.add("Mushrooms");
		toppings.add("Red Pepper");
	}
}
