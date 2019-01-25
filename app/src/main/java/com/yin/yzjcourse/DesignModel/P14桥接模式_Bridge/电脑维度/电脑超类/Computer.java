package com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑超类;

import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌超类.Brand;

/**
 * 电脑的超类；
 * 所有的电脑类型都需继承自Computer，内部维护了品牌的指针brand;
 * 而这个brand是超类Brand类型，可以是任何具体的品牌，而Computer可以是任何具体的电脑，因此
 * 也就实现了类似联想笔记本、神州台式机、戴尔笔记本等两个维度的组合。
 * 而这个brand就是桥接模式中的‘桥’，连接Computer维度和Brand维度。
 */
public class Computer {

    protected Brand brand;//这个就是桥，看注释

    /**
     * 构造时传入另一个维度Brand的引用
     * @param b
     */
    public Computer(Brand b) {
        this.brand = b;
    }

    /**
     *  可以调用另一个维度的方法
     */
    public String getName() {
        return brand.getName();
    }
}