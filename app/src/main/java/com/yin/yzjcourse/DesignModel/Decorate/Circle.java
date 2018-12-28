package com.yin.yzjcourse.DesignModel.Decorate;

import com.yin.yzjcourse.utils.DLog;

public class Circle implements Shape {

    @Override
    public void draw() {
        DLog.eLog("Shape: Circle");
    }
}
