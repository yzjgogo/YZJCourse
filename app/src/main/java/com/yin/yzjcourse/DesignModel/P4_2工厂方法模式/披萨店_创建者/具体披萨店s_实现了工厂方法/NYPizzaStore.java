package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleCheesePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleClamPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStylePepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleVeggiePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.抽象披萨店_工厂方法模式.PizzaStore;

public class NYPizzaStore extends PizzaStore {

	public Pizza createPizza(String item) {
		if (item.equals("cheese")) {
			return new NYStyleCheesePizza();
		} else if (item.equals("veggie")) {
			return new NYStyleVeggiePizza();
		} else if (item.equals("clam")) {
			return new NYStyleClamPizza();
		} else if (item.equals("pepperoni")) {
			return new NYStylePepperoniPizza();
		} else return null;
	}
}
