package com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.具体的建造者s;

import com.yin.yzjcourse.DesignModel.P13生成器模式_Builder.抽象的建造者.Builder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 具体的建造者
 * 用来建造实物；
 */
public class HTMLBuilder extends Builder {
    /**
     * 这里是创建一个字符串对象，但一般都是创建任意自定义类的对象,例如AlertDialog,一栋大楼等
     * 这里假设创建filename比较复杂，因此分布创建
     */
    private String filename;                                                        // 文件名
    private PrintWriter writer;                                                     // 用于编写文件的PrintWriter

    //创建这个buffer的步骤1
    @Override
    public void makeTitle(String title) {                                           // HTML文件的标题
        filename = title + ".html";                                                 // 将标题作为文件名
        try {
            writer = new PrintWriter(new FileWriter(filename));                     // 生成 PrintWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");    // 输出标题
        writer.println("<h1>" + title + "</h1>");
    }                                                    // 用于编写文件的PrintWriter

    //创建这个buffer的步骤2
    @Override
    public void makeString(String str) {                                            // HTML文件中的字符串
        writer.println("<p>" + str + "</p>");                                       // 用<p>标签输出
    }                                                    // 用于编写文件的PrintWriter

    //创建这个buffer的步骤3
    @Override
    public void makeItems(String[] items) {                                         // HTML文件中的条目
        writer.println("<ul>");                                                     // 用<ul>和<li>输出
        for (int i = 0; i < items.length; i++) {
            writer.println("<li>" + items[i] + "</li>");
        }
        writer.println("</ul>");
    }                                                    // 用于编写文件的PrintWriter

    //创建这个buffer的步骤4
    @Override
    public void close() {                                                           // 完成文档
        writer.println("</body></html>");                                           // 关闭标签
        writer.close();                                                             // 关闭文件
    }

    /**
     * 这个方法是具体的建造者自己的方法，Builder没有此方法
     * @return
     */
    public String create() {                                                     // 编写完成的文档
        return filename;                                                            // 返回文件名
    }
}
