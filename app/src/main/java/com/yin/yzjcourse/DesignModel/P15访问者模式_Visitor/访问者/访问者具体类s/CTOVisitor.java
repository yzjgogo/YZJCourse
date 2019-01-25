package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者具体类s;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Engineer;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Manager;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类.Visitor;

/**
 * 这是一个具体的访问者，最终由被访问元素的accept(Visitor ceoVisitor)接收；
 * 每一个具体的访问者都可以访问到数据结构mStaffs中的所有的元素，因为mStaffs开始
 * 遍历时会接收一个访问者，然后让这个访问者作为accept(visitor)的参数传递给每一个元素，参考
 * BusinessReport.showReport(Visitor visitor)方法；
 */
public class CTOVisitor implements Visitor {
    @Override
    public void visit(Engineer engineer) {
        System.out.println("工程师：" + engineer.name + ", 代码数量:" + engineer.getCodeLines());
    }
    @Override
    public void visit(Manager manager) {
        System.out.println("经理：" + manager.name +", 产品数量 ：" + manager.getProducts());
    }
}
