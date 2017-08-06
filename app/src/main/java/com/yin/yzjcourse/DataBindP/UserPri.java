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
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
}
