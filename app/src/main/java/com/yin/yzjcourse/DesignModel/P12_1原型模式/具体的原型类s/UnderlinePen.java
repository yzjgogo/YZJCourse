package com.yin.yzjcourse.DesignModel.P12_1原型模式.具体的原型类s;


import com.yin.yzjcourse.DesignModel.P12_1原型模式.抽象的原型类.Product;

public class UnderlinePen implements Product {
    private char ulchar;
    //构造器参数业务需要，不用关心
    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }
    //use()方法，业务需要，不用关心
    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\""  + s + "\"");
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println("");
    }

    /**
     * 该方法中调用clone()克隆自身
     * @return
     *
     *
     */
    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
