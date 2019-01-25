package com.yin.yzjcourse.DesignModel.P16责任链模式;

import com.yin.yzjcourse.DesignModel.P16责任链模式.链节1组长.GroupLeader;
import com.yin.yzjcourse.DesignModel.P16责任链模式.链节2主管.Director;
import com.yin.yzjcourse.DesignModel.P16责任链模式.链节3经理.Manager;
import com.yin.yzjcourse.DesignModel.P16责任链模式.链节4老板.Boss;

/**
 * 这个案例的意思是：员工需要报销X块钱，组长只有权报销1000，主管只有权报销5000，经理只有权报销10000，老板可以报销所有。
 * 这样需要一级级的判断这X块钱，谁能报销。
 *
 * 责任链模式：
 * 使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，
 * 直到有对象处理它为止。
 *
 * 解释：对于这条链，将每一个链节看成是一个对象，每一个对象都拥有不同的处理请求的权限和逻辑，将一个请求从链条的首端发出，
 * 沿着链条的路径依次传递给每一个链节对象，直到某个链节对象有权限处理这个请求位置。
 *
 * 注意事项：
 * 责任链模式中的每一个链节对象要么处理请求，要么把请求交给下一个处理者，不可以自己处理请求后还交给下一个处理者；
 * 一个请求最终只有两种结果：
 * 1：这个请求被某个链节对象处理了，这成为纯责任链；
 * 2：最终责任链的所有链节对象都没有处理这个请求，这成为不纯的责任链；
 *
 * 适用场景：
 * 对应同一个请求，多个对象都可能可以处理，单具体哪一个对象可以处理需要运行时动态决定。
 *
 * 责任链模式最小化：
 * 每一个链节都有共同的超类；
 * 每一个链节都持有下一个链节的引用；
 * 满足以上两点就是责任链模式。
 *
 *
 *  设计原则：
 *  封装变化：这个链条中每一个对象对请求都有不同的处理方式，而这个处理方式定义在基类里，各个子类具体实现；
 *  多用组合，少用继承：每一个链节的下一个处理者都是以者的方式存在的；
 *  针对接口编程，不针对实现编程：所有的链节对象都有共同的基类；
 *  为交互对象之间的松耦合设计而努力：请求的发送者和接收者是完全解耦的；
 *  类应该对扩展开放，对修改关闭(开闭原则)：可以扩展意义多的链节对象；
 *  依赖抽象，不依赖具体类(依赖倒置原则)：
 *  只和朋友交谈(最少知识原则)：
 *  别找我，我会找你(好莱坞原则)：
 *  类应该只有一个改变的理由(单一职责原则)：
 *
 *  责任链模式UML图：chain_1.png
 */
public class ChainTest {
    public static void main(String[] args) {
        //构造各个领导对象
        GroupLeader groupLeader = new GroupLeader();
        Director director = new Director();
        Manager manager = new Manager();
        Boss boss = new Boss();

        //设置上一级领导处理者对象，如果nextHandler定义成public的则可以这样调用
//        groupLeader.nextHandler = director;
//        director.nextHandler = manager;
//        manager.nextHandler = boss;

        //如果nextHandler是private且有setXXX方法，则可以这样调用，一般都 这样调用
        //也可以越级调用
        groupLeader.setNextHandler(director).setNextHandler(manager).setNextHandler(boss);

        //发起报账，会一级一级的判断，直到某一链节有条件处理就处理了
        groupLeader.dispatchRequest(50000);


    }
}
