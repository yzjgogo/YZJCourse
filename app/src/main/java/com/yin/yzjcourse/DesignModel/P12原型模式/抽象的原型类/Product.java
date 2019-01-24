package com.yin.yzjcourse.DesignModel.P12原型模式.抽象的原型类;
import java.lang.Cloneable;

/**
 * 1：各个具体的原型类都需实现该接口；
 * 2：该接口需继承Cloneable接口，Cloneable接口是一个空接口,是一个标记接口，表示实现了该
 *      接口的类或子类都可以调用clone()方法复制自身，否则调用clone()方法会报异常，注意
 *      clone()定义在Object中而不是定义在Cloneable接口中。
 *  3：我们用Product对Cloneable又封装了一层，其实你也可以 选择不封装，Cloneable本身就是抽象原型，这里Product是
 *      抽象原型。
 *
 */
public interface Product extends Cloneable {
    /**
     * 业务方法，可根据需要定义，不用关心
     * @param s
     */
    public abstract void use(String s);

    /**
     * 约定子类可以实现该方法，在该方法中调用clone()复制自身。
     * @return
     */
    public abstract Product createClone();
}
