package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.具体的原料工厂;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.BlackOlives;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.抽象Cheese.Cheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.抽象Clams.Clams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.抽象Dough.Dough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.Eggplant;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.具体Clamss.FrozenClams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.具体Cheeses.MozzarellaCheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.抽象Pepperoni.Pepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.具体Sauces.PlumTomatoSauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.抽象Sauce.Sauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.具体Pepperonis.SlicedPepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.Spinach;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.具体Doughs.ThickCrustDough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.抽象Veggies.Veggies;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.抽象的原料工厂_抽象工厂模式在这里.PizzaIngredientFactory;

public class ChicagoPizzaIngredientFactory
	implements PizzaIngredientFactory
{

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(),
		                      new Spinach(),
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
