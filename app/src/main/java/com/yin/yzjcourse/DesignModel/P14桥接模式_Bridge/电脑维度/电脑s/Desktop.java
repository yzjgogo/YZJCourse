package com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑s;

import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌超类.Brand;
import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.电脑维度.电脑超类.Computer;

/**
 * 具体的电脑类型
 */
public class Desktop extends Computer {

    public Desktop(Brand b) {
        super(b);
    }

    @Override
    public String getName() {
        return super.getName()+"台式机";
    }
}