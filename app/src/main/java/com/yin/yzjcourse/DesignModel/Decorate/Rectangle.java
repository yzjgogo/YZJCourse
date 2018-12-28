package com.yin.yzjcourse.DesignModel.Decorate;

import com.yin.yzjcourse.utils.DLog;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        DLog.eLog("Shape: Rectangle");
    }
}
