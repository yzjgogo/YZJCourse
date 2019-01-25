package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Engineer;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Manager;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问基类.Staff;

/**
 * 抽象的访问者基类，所有的具体的访问者都必须实现该接口；
 * 被访问的BusinessReport类中的数据结构mStaffs中的元素有几种类型就定义几个重载的visit(elementType)方法
 * 这里，有Engineer和Manager两种类型，因此分别定义了visit(Engineer engineer)和visit(Manager manager)两个方法；
 * 这样，我们在遍历mStaffs时，会对每一个元素都调用accept(visitor)方法，该方法会根据元素类型类型调用visitor对应的
 * visit(elementType)方法，这样既能保证访问者能够访问到每一个元素(accept(visitor))，也能让不同类型的元素得到不同
 * 的操作(visit(elementType))
 *
 * 由此可见，如果数据结构mStaffs中的元素类型经常增加，则会导致Visitor中定义的方法也会跟着增加，这样就应该考虑不适合用访问
 * 者模式。
 *
 * 注意，之所以定义多个重载的visit(elementType)方法，是因为如果只定义一个visit(Staff)方法，则还需在该方法内判断是哪个元素类型，出现if-else语句。这样不好。
 *
 * 每一个具体的访问者都可以访问到数据结构mStaffs中的所有的元素，因为mStaffs开始
 * 遍历时会接收一个访问者，然后让这个访问者作为accept(visitor)的参数传递给每一个元素，参考
 * BusinessReport.showReport(Visitor visitor)方法；
 */
public interface Visitor {
    /**
     * 访问工程师类型，如果被访问的元素是Engineer类型，则调用该方法
     */
    public void visit(Engineer engineer);
    /**
     * 访问经理类型，如果被访问的元素是Manager类型，则调用该方法
     */
    public void visit(Manager manager);

    /**
     * 访问者模式不应该有这个方法，因为访问者模式的目的是根据数据结构mStaffs中
     * 不同的元素类型，调用不同的visit(elementType)方法,而Staff是所有元素的
     * 超类型，违背了访问者模式的初衷。
     */
//    public void visit(Staff staff);
}
