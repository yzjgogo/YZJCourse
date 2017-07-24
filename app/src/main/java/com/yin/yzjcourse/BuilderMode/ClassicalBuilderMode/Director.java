package com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode;

/**
 * Created by think on 2017/7/24.
 */

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Director createCookies() {
        this.builder.setShape();
        return this;
    }

    public Cookies getCookies() {
        return this.builder.getCookies();
    }
}
