package com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode;

/**
 * Created by think on 2017/7/24.
 */

public class RoundCookiesBuilder implements Builder {
    private Cookies cookies;

    public RoundCookiesBuilder() {
        this.cookies = new Cookies();
    }

    @Override
    public void setShape() {
        this.cookies.setShape("圆形");
    }

    @Override
    public Cookies getCookies() {
        return this.cookies;
    }
}
