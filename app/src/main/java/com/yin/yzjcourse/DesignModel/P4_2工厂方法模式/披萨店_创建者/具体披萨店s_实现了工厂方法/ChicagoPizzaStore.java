package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleCheesePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleClamPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStylePepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleVeggiePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.抽象披萨店_工厂方法模式.PizzaStore;

public class ChicagoPizzaStore extends PizzaStore {

    //工厂方法，创建根据情况创建具体的对象
	public Pizza createPizza(String item) {
        	if (item.equals("cheese")) {
            		return new ChicagoStyleCheesePizza();
        	} else if (item.equals("veggie")) {
        	    	return new ChicagoStyleVeggiePizza();
        	} else if (item.equals("clam")) {
        	    	return new ChicagoStyleClamPizza();
        	} else if (item.equals("pepperoni")) {
            		return new ChicagoStylePepperoniPizza();
        	} else return null;
	}
}
