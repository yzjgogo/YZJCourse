package com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.具体的建造者s;

import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.抽象的建造者.Builder;

/**
 * 一个具体的建造者
 */
public class TextBuilder extends Builder {
    /**
     * 同样
     * 这里是创建一个StringBuffer对象，但一般都是创建较复杂的对象，例如AlertDialog，一栋大楼等。
     * 这里假设buffer比较复杂，因此咱们分布创建
     */
    private StringBuffer buffer = new StringBuffer();           // 文档内容保存在该字段中

    //创建这个buffer的步骤1
    @Override
    public void makeTitle(String title) {                       // 纯文本的标题
        buffer.append("==============================\n");      // 装饰线
        buffer.append("『" + title + "』\n");                   // 为标题添加『』
        buffer.append("\n");                                    // 换行
    }

    //创建这个buffer的步骤2
    @Override
    public void makeString(String str) {                        // 纯文本的字符串
        buffer.append('■' + str + "\n");                       // 为字符串添加■
        buffer.append("\n");                                    // 换行
    }

    //创建这个buffer的步骤3
    @Override
    public void makeItems(String[] items) {                     // 纯文本的条目
        for (int i = 0; i < items.length; i++) {
            buffer.append("　・" + items[i] + "\n");            // 为条目添加・
        }
        buffer.append("\n");                                    // 换行
    }

    //创建这个buffer的步骤4
    @Override
    public void close() {                                       // 完成文档
        buffer.append("==============================\n");      // 装饰线
    }


    /**
     * 这个方法是具体的建造者自己的方法，Builder没有此方法
     *
     * @return
     */
    public String create() {                                 // 完成的文档
        return buffer.toString();                               // 将StringBuffer变换为String
    }
}
