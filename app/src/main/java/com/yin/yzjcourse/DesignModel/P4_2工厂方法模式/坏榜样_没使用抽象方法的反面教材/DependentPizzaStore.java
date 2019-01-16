package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.坏榜样_没使用抽象方法的反面教材;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleCheesePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleClamPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStylePepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的纽约披萨s.NYStyleVeggiePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleCheesePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleClamPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStylePepperoniPizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleVeggiePizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;

/**
 * 依赖很严重，依赖了各种具体的披萨类，紧耦合，无法维护
 */
public class DependentPizzaStore {

    public Pizza createPizza(String style, String type) {
        Pizza pizza = null;
        if (style.equals("NY")) {
            if (type.equals("cheese")) {
                pizza = new NYStyleCheesePizza();
            } else if (type.equals("veggie")) {
                pizza = new NYStyleVeggiePizza();
            } else if (type.equals("clam")) {
                pizza = new NYStyleClamPizza();
            } else if (type.equals("pepperoni")) {
                pizza = new NYStylePepperoniPizza();
            }
        } else if (style.equals("Chicago")) {
            if (type.equals("cheese")) {
                pizza = new ChicagoStyleCheesePizza();
            } else if (type.equals("veggie")) {
                pizza = new ChicagoStyleVeggiePizza();
            } else if (type.equals("clam")) {
                pizza = new ChicagoStyleClamPizza();
            } else if (type.equals("pepperoni")) {
                pizza = new ChicagoStylePepperoniPizza();
            }
        } else {
            System.out.println("Error: invalid type of pizza");
            return null;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
