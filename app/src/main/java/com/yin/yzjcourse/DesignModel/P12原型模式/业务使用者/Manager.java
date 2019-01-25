package com.yin.yzjcourse.DesignModel.P12原型模式.业务使用者;

import com.yin.yzjcourse.DesignModel.P12原型模式.抽象的原型类.Product;

import java.util.HashMap;

/**
 * 客户端代码，就是使用原型获取实例的使用者
 *
 *
 */
public class Manager {
    private HashMap showcase = new HashMap();
    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    /**
     * showcase.get(protoname)就是一个原型
     * 返回值是一个新的实例，通过原型获取实例而不是通过new
     * @param protoname
     * @return
     */
    public Product create(String protoname) {
        Product p = (Product)showcase.get(protoname);
        return p.createClone();
    }
}
