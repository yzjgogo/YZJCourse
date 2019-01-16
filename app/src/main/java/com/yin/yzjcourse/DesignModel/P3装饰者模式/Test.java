package com.yin.yzjcourse.DesignModel.P3装饰者模式;

import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的调料_装饰者.Mocha;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的调料_装饰者.Soy;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的调料_装饰者.Whip;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的饮料_被装饰者.DarkRoast;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的饮料_被装饰者.Espresso;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.具体的饮料_被装饰者.HouseBlend;
import com.yin.yzjcourse.DesignModel.P3装饰者模式.饮料基类.Beverage;

/**
 * 设计原则：
 * 封装变化：
 * 多用组合，少用继承：被装饰者组合到装饰者里面。
 * 针对接口编程，不针对实现编程：
 * 为交互对象之间的松耦合设计而努力：这里被装饰者和装饰者是松耦合的，装饰者只引用了被装饰者的抽象对象，不用关心具体是哪个对象
 * 装饰者和被装饰者可以随意更改，而互不影响。
 * #类应该对扩展开放，对修改关闭：观察者模式中，我们可以任意增删改观察者，而不需修改被观察者的任何代码；装饰者模式中
 * 装饰者模式中，我们无需修改被装饰者的任何代码即可在运行阶段动态扩展其功能。
 * <p>
 * <p>
 * 装饰者模式：动态的将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。
 * 参考图片:decorator_1.png、decorator_2.png
 */
public class Test {

    public static void main(String args[]) {
        //只要Espresso，不加调料
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()
                + " $" + beverage.cost());

        //DarkRoast加两份Mocha和一份Whip，加完之后还是一份饮料beverage2
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
                + " $" + beverage2.cost());

        //HouseBlend加一份Soy一份Mocha一份Whip，加完之后还是一份饮料beverage3
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
                + " $" + beverage3.cost());
    }
}
