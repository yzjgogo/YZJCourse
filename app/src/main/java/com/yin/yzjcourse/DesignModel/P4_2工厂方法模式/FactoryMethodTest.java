package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法.ChicagoPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法.NYPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.抽象披萨店_工厂方法模式.PizzaStore;

/**
 * 工厂方法模式：
 * 在父类中定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
 * 在父类{@link PizzaStore}中定义一个创建对象的接口(广义上的接口)，但由子类{@link ChicagoPizzaStore}
 * 决定创建哪一个具体的实例。也就是说父类接收抽象对象{@link Pizza}，具体创建哪个具体类型的对象
 * {@link com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleClamPizza}
 * 由子类决定。
 *
 * 参考：
 * factory_method_1.jpg
 * factory_method_2.png
 * factory_method_3.png
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        //纽约的cheese披萨
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        //芝加哥的cheese披萨
        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");

        pizza = nyStore.orderPizza("clam");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("clam");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");

        pizza = nyStore.orderPizza("pepperoni");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("pepperoni");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");

        pizza = nyStore.orderPizza("veggie");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("veggie");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
