package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨;

import java.util.ArrayList;
//披萨店，依赖抽象，不依赖具体的实例
public abstract class Pizza {
	public String name;
	public String dough;
	public String sauce;
	public ArrayList<String> toppings = new ArrayList<String>();

	public void prepare() {
		System.out.println("Prepare " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (String topping : toppings) {
			System.out.println("   " + topping);
		}
	}

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cut the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}
 
	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");
		display.append(dough + "\n");
		display.append(sauce + "\n");
		for (String topping : toppings) {
			display.append(topping + "\n");
		}
		return display.toString();
	}
}

 
 
