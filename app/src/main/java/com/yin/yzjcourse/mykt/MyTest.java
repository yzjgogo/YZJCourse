package com.yin.yzjcourse.mykt;

import android.util.Log;

import com.yin.yzjcourse.utils.DLog;

public class MyTest {//@@
    public int get(int x,int y){
        DLog.eTest("why");
        Person person = new Person(12,"jack");
        Person a = person;
        Person b = person;
        if (a.equals(b)) {
            DLog.eTest("相等");
        }else {
            DLog.eTest("不相等");
        }
        return x+y;
    }
}
