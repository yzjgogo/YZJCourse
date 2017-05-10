package com.yin.yzjcourse.DiyWidget.PieGraph;

/**
 * Created by think on 2017/5/10.
 */

public class PieSlice {

    private int color;
    private float value;

    public PieSlice(int color, float value) {
        this.color = color;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
