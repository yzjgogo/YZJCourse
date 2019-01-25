package com.yin.yzjcourse.DesignModel.P16责任链模式.链节3经理;

import com.yin.yzjcourse.DesignModel.P16责任链模式.所有链节基类.Leader;

public class Manager extends Leader {
    @Override
    protected int limit() {
        return 10000;
    }

    @Override
    protected void handle(int money) {
        System.out.println("经理批复宝箱:"+money+"元");
    }
}
