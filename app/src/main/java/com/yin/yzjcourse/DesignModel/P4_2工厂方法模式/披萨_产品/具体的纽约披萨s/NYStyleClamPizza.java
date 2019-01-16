package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;

public class NYStyleClamPizza extends Pizza {

	public NYStyleClamPizza() {
		name = "NY Style Clam Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
 
		toppings.add("Grated Reggiano Cheese");
		toppings.add("Fresh Clams from Long Island Sound");
	}
}
