package com.yin.yzjcourse.DesignModel.P13生成器模式_Builder;

import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.具体的建造者s.HTMLBuilder;
import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.具体的建造者s.TextBuilder;
import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.构建车间_Builder的工作室.Director;

/**
 *  生成器模式：
 *  用于将复杂对象的创建过程进行拆分，将该对象的创建过程分步进行，例如抽象Builder里定义的抽象方法，就是创建对象的步骤。
 *
 *
 *  生成器模式的关注点：
 *  1：抽象的Builder和具体的Builder就构成了生成器模式;
 *  2：Director不是必须的，例如AlertDialog的链式调用
 *  new TextBuilder().makeTitle("").makeString("").makeItems("").create()只需Builder的每个方法
 *  返回自身this即可。
 *  3：Builder模式是用来创建复杂对象的，跟new一个对象是一个道理，具体创建对象的过程在具体的TextBuilder里实现。
 *
 *
 *  使用场景：
 *  一个对象的初始化(创建)太复杂，例如参数太多等(很多时候这些参数都有默认值)；
 *
 *
 *  设计原则：
 *  封装变化：将复杂对象的创建过程，用几个方法约束；
 *  多用组合，少用集成：Director中组合进了Builder；
 *  针对接口编程，不针对实现编程：任何具体的builder都必须继承Builder抽象类；
 *  为交互对象之间的松耦合设计而努力：客户代码创建对象的过程，与对象内部 的具体实现是松耦合的；
 *  类应该对扩展开放，对修改关闭(开闭原则)：
 *  依赖抽象，不依赖具体类(依赖倒置原则)：Director依赖抽象的Builder;
 *  只和朋友交谈(最少知识原则)：Director调用了它的属性builder的方法；
 *  别找我，我会找你(好莱坞原则)：Director单向调用了它的属性builder的方法；
 *  类应该只有一个改变的理由(单一职责原则)：具体的builder类只用来创建指定的对象；
 *
 *  代码实例类图 ：builder_p_1.png
 *  Builder模式UML图：builder_p_2.png
 *
 */
public class BuilderTest {
    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(0);
        }
        if (args[0].equals("plain")) {
            /**
             * 使用TextBuilder
             */
            //1，text的创建器
            TextBuilder textbuilder = new TextBuilder();
            //2，拿到director使用textbuilder统一组装
            Director director = new Director(textbuilder);
            director.construct();
            //3，组装后，取出创建结果对象
            String result = textbuilder.create();
            System.out.println(result);
        } else if (args[0].equals("html")) {
            /**
             * 使用HTMLBuilder
             */
            HTMLBuilder htmlbuilder = new HTMLBuilder();
            Director director = new Director(htmlbuilder);
            director.construct();
            String filename = htmlbuilder.create();
            System.out.println(filename + "文件编写完成。");
        } else {
            usage();
            System.exit(0);
        }
    }
    public static void usage() {
        System.out.println("Usage: java BuilderTest plain      编写纯文本文档");
        System.out.println("Usage: java BuilderTest html       编写HTML文档");
    }
}
