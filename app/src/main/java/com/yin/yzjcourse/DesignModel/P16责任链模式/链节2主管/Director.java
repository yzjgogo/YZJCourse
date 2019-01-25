package com.yin.yzjcourse.DesignModel.P16责任链模式.链节2主管;

import com.yin.yzjcourse.DesignModel.P16责任链模式.所有链节基类.Leader;

public class Director extends Leader {
    @Override
    protected int limit() {
        return 5000;
    }

    @Override
    protected void handle(int money) {

        System.out.println("主管批复报销了 :" +money+"元");
    }
}
