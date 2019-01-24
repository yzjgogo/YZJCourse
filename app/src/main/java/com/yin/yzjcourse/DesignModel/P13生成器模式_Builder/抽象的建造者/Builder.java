package com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.抽象的建造者;

/**
 * 所有的具体建造者必须继承它；
 * 定义了一组建造实物必须的方法；
 * obj的构造比较复杂，例如一辆汽车，一栋大楼等，因此需要分布创建，该抽象类定义的这些方法就是创建obj的各个步骤
 */
public abstract class Builder {
//    Object obj; 子类中一般会有一个对象，这个对象就是Builder要创建的对象，创建这个对象比较复杂，因此分布创建
    //创建obj步骤1
    public abstract void makeTitle(String title);
    //创建obj步骤2
    public abstract void makeString(String str);
    //创建obj步骤3
    public abstract void makeItems(String[] items);
    //创建obj步骤4
    public abstract void close();
}
