package com.yin.yzjcourse.DesignModel.P16责任链模式.链节4老板;

import com.yin.yzjcourse.DesignModel.P16责任链模式.所有链节基类.Leader;

public class Boss extends Leader {
    @Override
    protected int limit() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void handle(int money) {

        System.out.println("老板批复报销 :"+money+"元");
    }
}
