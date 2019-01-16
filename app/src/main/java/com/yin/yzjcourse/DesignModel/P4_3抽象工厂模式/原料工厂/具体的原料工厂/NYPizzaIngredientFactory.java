package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.具体的原料工厂;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.抽象Cheese.Cheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.抽象Clams.Clams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.抽象Dough.Dough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Clams.具体Clamss.FreshClams;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.Garlic;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.具体Sauces.MarinaraSauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.Mushroom;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.Onion;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.抽象Pepperoni.Pepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.具体Veggiess.RedPepper;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Cheese.具体Cheeses.ReggianoCheese;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Sauce.抽象Sauce.Sauce;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Pepperoni.具体Pepperonis.SlicedPepperoni;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Dough.具体Doughs.ThinCrustDough;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料.Veggies.抽象Veggies.Veggies;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.抽象的原料工厂_抽象工厂模式在这里.PizzaIngredientFactory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
