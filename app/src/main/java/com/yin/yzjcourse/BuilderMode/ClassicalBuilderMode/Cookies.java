package com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode;

/**
 * 经典的Builder模式有四个参与者

 Product：被构造的复杂对象
 Builder：抽象接口，用来定义创建Product对象的各个组成部
 件的操作。
 ConcreteBuilder：Builder接口的具体实现，可以定义多个，
 是实际构建Product对象的类，同时会提供一个返回Product的接
 口。
 Director：Builder接口的构造者和使用者。
 */

public class Cookies {
    private String shape;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Cookies{" +
                "shape='" + shape + '\'' +
                '}';
    }
}
