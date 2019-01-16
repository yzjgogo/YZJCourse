package com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.创建披萨的工厂;

import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s.CheesePizza;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s.ClamPizza;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s.PepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.抽象的披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_1简单工厂模式.披萨.具体的披萨s.VeggiePizza;

/**
 * 创建具体某种披萨的工厂，你需要什么样的具体对象，我就给你创建哪个，且以抽象对象方式返回，实现松耦合。
 *
 */
public class SimplePizzaFactory {

	//该方法也可以定义成static的
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new ClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}
}
