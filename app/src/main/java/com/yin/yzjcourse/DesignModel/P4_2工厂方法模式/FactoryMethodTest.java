package com.yin.yzjcourse.DesignModel.P4_2工厂方法模式;

import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法.ChicagoPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.具体披萨店s_实现了工厂方法.NYPizzaStore;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.抽象披萨.Pizza;
import com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨店_创建者.抽象披萨店_工厂方法模式.PizzaStore;

/**
 * 注意所有的工厂模式最终目的是用来封装对象的创建
 *
 * 工厂方法模式：
 * 在父类中定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
 * 在父类{@link PizzaStore}中定义一个创建对象的接口(广义上的接口)，但由子类{@link ChicagoPizzaStore}
 * 决定创建哪一个具体的实例。也就是说父类接收抽象对象{@link Pizza}，具体创建哪个具体类型的对象
 * {@link com.yin.yzjcourse.DesignModel.P4_2工厂方法模式.披萨_产品.具体的芝加哥披萨s.ChicagoStyleClamPizza}
 * 由子类决定。
 *
 * 简单来说，工厂方法使用继承，把对象的创建委托给子类，子类实现工厂方法来创建对象。
 *
 * 参考：
 * factory_method_1.jpg
 * factory_method_2.png
 * factory_method_3.png
 *
 *
 * 设计原则：
 * 多用组合、少用继承：
 * 针对接口编程，不针对实现编程：这里，工厂方法模式依赖抽象的接口对象Pizza，而不是依赖具体的对象。
 * 为交互对象之间的松耦合设计而努力：这里，披萨店和披萨之间是松耦合的，披萨店只关心抽象的Pizza对象，不关心具体的披萨对象。
 * 类应该对扩展开放，对修改关闭：这里，抽象的PizzaStore不会因为Pizza类型的增减而有所变化，只需在子类的工厂方法中适当的处理即可。
 * 依赖倒置原则(依赖抽象，不要依赖具体类)：这个原则听起来像是‘针对接口编程，不针对实现编程’，但是这里更强调’抽象‘，
 *              这里，PizzaStore依赖抽象的Pizza而不是依赖具体的某个披萨类。
 * 别主动找我，我会叫你的(好莱坞原则)：父类单向调用子类的方法去创建对象，反之则没有，这是单向的。
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
