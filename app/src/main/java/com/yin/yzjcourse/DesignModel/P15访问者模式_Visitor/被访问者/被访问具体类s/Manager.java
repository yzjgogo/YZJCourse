package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问基类.Staff;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类.Visitor;

import java.util.Random;

/**
 * 经理
 * 具体的元素类型
 */
public class Manager extends Staff {
    private int products;//产品数量
    public Manager(String name) {
        super(name);
        products = new Random().nextInt(10);
    }

    /**
     * 会调用visitor对应的visit(Manager)方法
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    //一年内做的产品数量
    public int getProducts(){
        return products;
    }
}
