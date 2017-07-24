package com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode;

/**
 * Created by think on 2017/7/24.
 */

public class SquareCookiesBuilder implements Builder {
    private Cookies cookies;

    public SquareCookiesBuilder() {
        this.cookies = new Cookies();
    }

    @Override
    public void setShape() {
        this.cookies.setShape("方形");
    }

    @Override
    public Cookies getCookies() {
        return this.cookies;
    }
}
