package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;

/**
 * 一件事情当中所有参与者的共同基类；
 * 这里光驱、CPU、显卡、声卡都继承自Colleague，他们是一群同事；
 * 他们彼此之间相互独立，完全解耦，其中有一个发生变化不会影响另一个；
 * 当其中某个同事发生变化时，会通知中介者，由中介者将这个变化协调到其它同时；
 * 也就是说各个同事不相互作用，有啥事找中介者，中介者负责协调各个同事间的关系。
 */
public abstract class Colleague {
    /**
     * 因为中介者是所有同事的媒介，因此，每个同事都应持有该中介者的引用
     * 所以定义在所有同事共同的父类Colleague中。
     * 这样，当某个同事变化时，只需调用mediator.changed(Colleague c)即可，由该方法负责通知其它同事作出反应。
     */
    protected Mediator mediator;//每一个同事都该知道其中介者
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
