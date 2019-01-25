package com.yin.yzjcourse.DesignModel.P16责任链模式.链节1组长;

import com.yin.yzjcourse.DesignModel.P16责任链模式.所有链节基类.Leader;

public class GroupLeader extends Leader {
    /**
     * 该级别链节能够处理请求的条件时1000元以内
     * @return
     */
    @Override
    protected int limit() {
        return 1000;
    }

    /**
     * 该链节的处理方式是报销
     * @param money
     */
    @Override
    protected void handle(int money) {
        System.out.println("组长批复报销 :"+money+"元");
    }
}