package com.yin.yzjcourse.DataBindP;

/**
 * Created by Administrator on 2017/8/6.
 */

public class UserPri {
    private final String firstName;
    private final String lastName;
    public UserPri(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //也可以命名成getFirstName，即getFirstName()和firstName()都可以识别，识别的是方法名，不是return的值
    public String firstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
}
