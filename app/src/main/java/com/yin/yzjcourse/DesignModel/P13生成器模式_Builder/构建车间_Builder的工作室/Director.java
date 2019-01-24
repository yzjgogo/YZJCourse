package com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.构建车间_Builder的工作室;

import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.抽象的建造者.Builder;

/**
 * Builder的使用者，用于统一组装(创建)一个对象，将Builder的所有步骤统一起来
 * 其实Builder模式可以没有Director角色的，示业务需要而定，例如AlertDialog的链式调用
 * new TextBuilder().makeTitle("").makeString("").makeItems("").create()
 * 只需Builder的每个方法返回自身this即可。
 */
public class Director {
    private Builder builder;

    /**
     * 参数是Builder类型
     * @param builder
     */
    public Director(Builder builder) {              // 因为接收的参数是Builder类的子类
        this.builder = builder;                     // 所以可以将其保存在builder字段中
    }

    /**
     * 该方法实际去调用一系列builder的方法
     */
    public void construct() {                       // 编写文档
        builder.makeTitle("Greeting");              // 标题
        builder.makeString("从早上至下午");         // 字符串
        builder.makeItems(new String[]{             // 条目
            "早上好。",
            "下午好。",
        });
        builder.makeString("晚上");                 // 其他字符串
        builder.makeItems(new String[]{             // 其他条目
            "晚上好。",
            "晚安。",
            "再见。",
        });
        builder.close();                            // 完成文档
    }
}
