package com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式;

import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨.抽象的披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.具体的披萨店s.ChicagoPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.具体的披萨店s.NYPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.披萨店.抽象的披萨店_工厂方法模式在这里.PizzaStore;

/**
 * 注意这个案例既用到了‘工厂方法模式’又用到了‘抽象工厂模式’，‘工厂方法模式’前面已经单讲了，这里只需关注‘抽象工厂模式即可’
 *
 * 注意所有的工厂模式最终目的是用来封装对象的创建。
 *
 * 抽象工厂模式：
 * 提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。
 *
 * 简单来说，抽象工厂使用对象组合，对象的创建被实现在工厂接口所暴露出来的方法中。
 *
 * 参考{@link com.yin.yzjcourse.DesignModel.P4_3抽象工厂模式.原料工厂.抽象的原料工厂_抽象工厂模式在这里.PizzaIngredientFactory}
 * 参考:abs_factory_1.png  ， abs_factory_2.jpg
 */
public class AbstractFactoryTest {
 
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
}
