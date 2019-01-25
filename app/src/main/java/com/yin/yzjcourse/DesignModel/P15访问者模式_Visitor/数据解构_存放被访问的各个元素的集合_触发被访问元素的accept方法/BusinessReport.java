package com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.数据解构_存放被访问的各个元素的集合_触发被访问元素的accept方法;

import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Engineer;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问具体类s.Manager;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.被访问者.被访问基类.Staff;
import com.yin.yzjcourse.DesignModel.P15访问者模式_Visitor.访问者.访问者基类.Visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * 员工业务报表类（ObjectStructure）
 * 存放所有元素的数据结构，会遍历元素，调用元素的accept()方法，从而触发visitor的visit()方法
 */
public class BusinessReport {
    List<Staff> mStaffs = new LinkedList<Staff>();
    public BusinessReport() {
        mStaffs.add(new Manager("王经理"));
        mStaffs.add(new Engineer("工程师-Shawn.Xiong"));
        mStaffs.add(new Engineer("工程师-Kael"));
        mStaffs.add(new Engineer("工程师-Chaossss"));
        mStaffs.add(new Engineer("工程师-Tiiime"));
    }
    /**
     * 为访问者展示报表
     * 遍历数据结构中的所有元素
     * @param visitor 公司高层，如CEO、CTO
     */
    public void showReport(Visitor visitor){
        for(Staff staff : mStaffs){
            staff.accept(visitor);
        }
    }
}
