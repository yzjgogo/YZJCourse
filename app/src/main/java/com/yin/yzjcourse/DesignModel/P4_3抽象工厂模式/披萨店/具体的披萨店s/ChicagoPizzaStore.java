package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.具体的披萨店s;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.具体的披萨s.CheesePizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.具体的原料工厂.ChicagoPizzaIngredientFactory;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.具体的披萨s.ClamPizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.具体的披萨s.PepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.抽象的披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.抽象的原料工厂_抽象工厂模式在这里.PizzaIngredientFactory;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.具体的披萨s.VeggiePizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.抽象的披萨店_工厂方法模式在这里.PizzaStore;

public class ChicagoPizzaStore extends PizzaStore {

	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory =
		new ChicagoPizzaIngredientFactory();

		if (item.equals("cheese")) {

			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza");

		} else if (item.equals("veggie")) {

			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("Chicago Style Veggie Pizza");

		} else if (item.equals("clam")) {

			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza");

		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("Chicago Style Pepperoni Pizza");

		}
		return pizza;
	}
}
