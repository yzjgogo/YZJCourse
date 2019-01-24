package com.yin.yzjcourse.DesignModel.P12原型模式;


import com.yin.yzjcourse.DesignModel.P12原型模式.业务使用者.Manager;
import com.yin.yzjcourse.DesignModel.P12原型模式.具体的原型类s.MessageBox;
import com.yin.yzjcourse.DesignModel.P12原型模式.具体的原型类s.UnderlinePen;
import com.yin.yzjcourse.DesignModel.P12原型模式.抽象的原型类.Product;

/**
 * 原型模式：
 * 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。
 *
 *
 * 关键点：
 * 原型模式精髓，根据一个对象复制出一个新的实例，不要被示例代码所迷惑，关注这一点即可，无论业务逻辑怎么变，这一点是不变的。
 *
 *
 * 注意：
 * 1：这里的‘原型’就是被赋值的对象，例如MessageBox实例、UnderlinePen实例，意思就是复制出的对象原来的模型。
 * 2：赋值出的结果就是新的实例；通过赋值替代了new;
 * 3：原型模式其实就是new Object()的替换模式；
 * 4：原型模式不一定必须使用Cloneable接口和clone()方法，只要你能根据原型对象获取新的对象都可以，无论你采取什么方式。
 *
 *
 * 使用场景：
 * 总的来说就是不适合用new Object()的方式的时候就可以用原型模式；
 * 1：创建一个对象比较复杂、耗时；
 * 2：类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等；
 * 3：一个对象A需要提供给其它多个对象访问，而这些对象有可能修改A，此时为了保护A可以A为原型赋值出新的A1，让
 *      这些对象来操作A1，从而保护A。
 *  clone()的过程只是针对已存在的原型的对象属性等的复制，从而避开了上述弱势情况，实现新对象的获取，因为
 *  clone()的过程本身也是比较慢的，所以除非以上几种情况比clone()更糟糕，否则尽量不要使用原型模式。
 *
 *
 *  设计原则：
 *  封装变化：
 *  多用组合，少用集成：
 *  针对接口编程，不针对实现编程：
 *  为交互对象之间的松耦合设计而努力：
 *  类应该对扩展开放，对修改关闭(开闭原则)：
 *  依赖抽象，不依赖具体类(依赖倒置原则)：
 *  只和朋友交谈(最少知识原则)：
 *  别找我，我会找你(好莱坞原则)：
 *  类应该只有一个改变的理由(单一职责原则)：
 *
 *  示例类图 ：proto_sample_1.png
 *  原型模式uml图：proto_sample_2.png
 */
public class ProtoTypeTest {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');//一个原型
        MessageBox mbox = new MessageBox('*');//一个原型
        MessageBox sbox = new MessageBox('/');//一个原型
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        // 生成，使用原型模式获取 新对象
        Product p1 = manager.create("strong message");
        p1.use("Hello, world.");
        Product p2 = manager.create("warning box");
        p2.use("Hello, world.");
        Product p3 = manager.create("slash box");
        p3.use("Hello, world.");
    }
}
