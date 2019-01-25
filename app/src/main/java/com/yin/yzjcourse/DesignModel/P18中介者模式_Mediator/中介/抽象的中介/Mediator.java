package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;

public abstract class Mediator {
    /**
     * 同事对象改变时通知中介者的方法
     * 在同事对象改变时由中介者去通知其他的同事对象
     *
     * 这里就是中介者发挥作用的地方，哪个同时有变化都会调用该方法，然后在具体的中介者类中会处理
     * @param c 同事对象
     */
    public abstract void changed(Colleague c);
}
