package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.数据解构_存放被访问的各个元素的集合_触发被访问元素的accept方法.BusinessReport;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者具体类s.CEOVisitor;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者具体类s.CTOVisitor;

/**
 * 访问者模式：
 * 封装一些作用于某种数据结构中的各元素的操作，它可以在不改变这个数据结构的前提下定义作用于这些元素的新操作。
 *
 * 解释：数据结构类似于一个集合，这个集合的所有元素虽然有共同的超类型(Staff)，但是具体的元素类型是有限的(只有Engineer和Manager)
 * 每一个元素都有accept(visitor)方法，在accept(visitor)方法中，会根据当前的元素类型(Engineer)调用visitor的对应的
 * visit(elementType)(visit(Engineer))方法。这样在遍历这个数据结构时，触发每一个元素的accept()最终触发visit(elementType)方法
 * 使各个类型的元素都能得到操作，同时也不用修改数据结构所在的类，实现元素的操作和数据结构解耦。
 *
 * 访问者模式最小化：
 * 1：被访问者的accept(visitor)方法；
 * 2：访问器根据不同的元素类型定义不同的visit(elementType)方法；
 * 3：存放所有元素的数据结构mStaffs;
 * 4：遍历这个数据结构访问每一个元素；
 * 5：元素的类型较固定，不经常增减元素类型，因为如果增减元素类型会导致Visitor的visit(elementType)方法的增减；
 *
 * 使用场景：
 * 1：数据结构较稳定，元素类型也较稳定；
 * 2：希望对元素的操作的同时，不修改数据结构所在类BusinessReport.
 *
 *
 *  设计原则：
 *  封装变化：对元素的操作经常变化，封装到Visitor.visit(elementType)方法中；
 *  多用组合，少用继承：
 *  针对接口编程，不针对实现编程：被访问者和访问器都有超类接口；
 *  为交互对象之间的松耦合设计而努力：数据结构与访问器是完全解耦的；
 *  类应该对扩展开放，对修改关闭(开闭原则)：想添加新的访问器，只需创建一个实现Visitor的接口即可，BusinessReport无需修改；
 *  依赖抽象，不依赖具体类(依赖倒置原则)：BusinessReport和被访问元素的accept(visitor)都依赖抽象的Visitor，不依赖具体的访问者；
 *  只和朋友交谈(最少知识原则)：不存在访问非朋友方法的情况；
 *  别找我，我会找你(好莱坞原则)：
 *  类应该只有一个改变的理由(单一职责原则)：BusinessReport只负责管理数据结构，不负责操作元素；
 *
 *  访问者模式UML：visitor_p_1.png
 *  示例代码图：visitor_p_2.png
 *
 */
public class VisitorTest {
    public static void main(String[] args) {
        //构建报表,构建数据结构
        BusinessReport report = new BusinessReport();
        System.out.println("===== 给CEO看报表 =====");
        //设置访问者，这里是CEO，遍历数据结构，触发每一个元素的accept()方法，调用对应的visit()方法
        report.showReport(new CEOVisitor());
        System.out.println("===== 给CTO看报表 =====");
        //设置另一个访问者 CTO
        report.showReport(new CTOVisitor());
    }
}
