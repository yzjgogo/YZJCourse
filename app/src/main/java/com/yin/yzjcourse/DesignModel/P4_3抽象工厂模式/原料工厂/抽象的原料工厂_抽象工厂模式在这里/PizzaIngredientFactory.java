package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.抽象的原料工厂_抽象工厂模式在这里;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.抽象Cheese.Cheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.抽象Clams.Clams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.抽象Dough.Dough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.抽象Pepperoni.Pepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.抽象Sauce.Sauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.抽象Veggies.Veggies;

/**
 * Dough、Sauce、Cheese、Veggies、Pepperoni、Clams这些类在业务上有一定的相关性。
 * 实际上可以根据需要创建这些类的任意子类实例。
 */
public interface PizzaIngredientFactory {
 
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
 
}
