package com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌s;

import com.yin.yzjcourse.DesignModel.P14桥接模式_Bridge.品牌维度.品牌超类.Brand;

/**
 * 具体的品牌，继承自Brand
 */
public class Shenzhou implements Brand {
    @Override
    public String getName() {
        return "神州";
    }
}