package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问基类;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类.Visitor;

import java.util.Random;

/**
 * 员工基类（Element）
 * 数据结构mStaffs中的所有元素都是该类型，定义了所有元素公共的抽象accept(visitor)方法
 * 子类需实现accept(visitor)方法，调用visitor的对应的visit(elementType)方法。
 */
public abstract class Staff {
    //员工姓名
    public String name;
    //员工KPI
    public int kpi;
    public Staff(String name) {
        this.name = name;
        this.kpi = new Random().nextInt(10);
    }
    //接受Visitor的访问
    public abstract void accept(Visitor visitor);
}
