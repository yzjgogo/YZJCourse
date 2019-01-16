package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.抽象的披萨;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.抽象Cheese.Cheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.抽象Clams.Clams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.抽象Dough.Dough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.抽象Pepperoni.Pepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.抽象Sauce.Sauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.抽象Veggies.Veggies;

public abstract class Pizza {
	public String name;

    public Dough dough;
    public Sauce sauce;
    public Veggies veggies[];
    public Cheese cheese;
    public Pepperoni pepperoni;
    public Clams clam;

    public abstract void prepare();

    public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

    public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

    public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

    public void setName(String name) {
		this.name = name;
	}

    public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + name + " ----\n");
		if (dough != null) {
			result.append(dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append(sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append(cheese);
			result.append("\n");
		}
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}
		if (clam != null) {
			result.append(clam);
			result.append("\n");
		}
		if (pepperoni != null) {
			result.append(pepperoni);
			result.append("\n");
		}
		return result.toString();
	}
}
