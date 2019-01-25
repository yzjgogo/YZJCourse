package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问基类.Staff;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类.Visitor;

import java.util.Random;

/**
 * 工程师
 * 具体的一个元素类型
 */
public class Engineer extends Staff {
//    private int codeLines;//代码数量
    public Engineer(String name) {
        super(name);
//        codeLines = new Random().nextInt(10 * 10000);
    }

    /**
     * 会调用visitor的对应的visit(Enginner)方法
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    //工程师这一年写的代码数量
    public int getCodeLines(){
        return new Random().nextInt(10*10000);
    }
}
