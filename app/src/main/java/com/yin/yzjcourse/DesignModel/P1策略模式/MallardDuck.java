package com.yin.yzjcourse.DesignModel.P1策略模式;


import com.yin.yzjcourse.DesignModel.P1策略模式.叫唤行为.具体的叫唤类s.Quack;
import com.yin.yzjcourse.DesignModel.P1策略模式.飞行行为.具体的飞行类s.FlyWithWings;
import com.yin.yzjcourse.DesignModel.P1策略模式.鸭子基类.Duck;

public class MallardDuck extends Duck {

    public MallardDuck() {
        //Duck中已经定义了quackBehavior和flyBehavior，这个MallardDuck会通过Quack叫唤，通过FlyWithWings飞行
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();

    }

    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
