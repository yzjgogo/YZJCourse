package com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge;

import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌s.Lenovo;
import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌s.Shenzhou;
import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑s.Desktop;
import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑s.Laptop;
import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑超类.Computer;

/**
 * 第一种两个维度的事物举例：什么品牌的什么类型电脑
 * 神州笔记本、神州台式机、神州平板、联想笔记本、联想台式机、联想平板、戴尔笔记本、戴尔台式机、戴尔平板；
 *
 * 第二种两个维度的事物举例：什么规格杯的什么口味的开啡
 * 大杯加糖、大杯加奶、大杯原味、中杯加糖、中杯加奶、中杯原味、小杯加糖、小杯加奶，小杯原味；
 *
 *  桥接模式：
 * 一个事物由两个维度组成，而这两个维度又分别有很多类型，可以将这两个维度分别处理，然后让其中一个维度的基类引用另
 * 一个维度的基类型变量；
 * 其中“桥”就体现在，其中一个维度的基类持有另一个维度的基类型的引用，这个引用就是一个‘桥’，参考Computer的brand属性，
 * 这个桥就把两个维度连接起来。
 *
 * 如果不适用桥接模式，则因为两个维度都在变化，会导致类如下缺点：
 * 参考图片：no_bridge.png
 * 1：继承解构更深；
 * 2：类数量更多；
 * 3：单个类违反'单一职责原则'，因为一个类要同时维护两个维度。
 *
 * 桥接模式使用场景：
 * 一个事物有两个维度且两个维度有很多分类的情况，如上面的电脑和咖啡的例子；
 *
 * 桥接模式重点关注点：将两个维度分开单独处理，切让这两个维度建立连接(桥)，有这两样就是桥接模式，其它可不用关心；
 *
 *
 *
 *  设计原则：
 *  封装变化：Brand无论如何扩展，其getName()无论如何实现，Computer只需调用自己的getName()即可；
 *  多用组合，少用继承：桥接模式中Brand维度其实是组合进Computer维度的，从而以组合替代继承；
 *  针对接口编程，不针对实现编程：Brand是抽象接口，各个子类需实现该接口；
 *  为交互对象之间的松耦合设计而努力：两个维度是完全解耦的；
 *  类应该对扩展开放，对修改关闭(开闭原则)：Brand维度或Computer维度可以任意扩展。因为二者彼此互补关心具体的类型，只关心超类型；
 *  依赖抽象，不依赖具体类(依赖倒置原则)：Computer依赖Brand的抽象类型，不依赖具体类型；
 *  只和朋友交谈(最少知识原则)：brand是Computer的朋友，可以调用其方法；
 *  别找我，我会找你(好莱坞原则)：Computer单向调用brand的；
 *  类应该只有一个改变的理由(单一职责原则)：Computer和Brand都是单一职责；
 *
 * 已电脑为例：
 * 没有使用桥接的情况参考图片：no_bridge.png
 * 两个维度参考图片：two_dimens.png
 * 使用了桥接的情况参考图片：bridge_use.png
 * 桥接模式UML类图：bridge_p.png
 */
public class BridgeTest {
    public static void main(String[] args) {

        Computer c = new Laptop(new Lenovo());//联想笔记本
        c.getName();

        Computer c2 = new Desktop(new Shenzhou());//神州台式机
        c2.getName();


    }
}
