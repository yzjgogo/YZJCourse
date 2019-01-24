package com.yin.yzjcourse.DesignModel.P12原型模式.具体的原型类s;


import com.yin.yzjcourse.DesignModel.P12原型模式.抽象的原型类.Product;

/**
 * 具体的原型类，实现Product接口。
 */
public class MessageBox implements Product {
    private char decochar;

    //构造器参数是业务 需要，不用关心
    public MessageBox(char decochar) {
        this.decochar = decochar;
    }

    //use方法，业务需要，不用关心
    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
        System.out.println(decochar + " " + s + " " + decochar);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
    }

    /**
     * 具体的原型就是在该方法中调用clone()克隆自身。
     *
     * @return
     */
    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();//克隆自身
        } catch (CloneNotSupportedException e) {
            //如果超类没有实现Cloneable接口 就会报该异常
            e.printStackTrace();
        }
        return p;
    }
}
