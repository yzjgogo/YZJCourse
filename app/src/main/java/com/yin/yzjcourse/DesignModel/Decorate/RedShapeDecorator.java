package com.yin.yzjcourse.DesignModel.Decorate;

import com.yin.yzjcourse.utils.DLog;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        DLog.eLog("Border Color: Red");
    }
}
