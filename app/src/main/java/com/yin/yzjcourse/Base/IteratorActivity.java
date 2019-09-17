package com.yin.yzjcourse.Base;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Iterable是一个接口，我们常见的集合类，例如List都实现了Iterable，Iterable提供了iterator()方法，用户获取迭代器Iterator；
 * Iterator是迭代器，用于遍历集合内部的所有元素，提供了boolean hasNext()、E next()、remove()方法，这三个方法必须按顺序
 *      调用(问-->取-->删)，其中”删”不是必须的，调用remove()之前必须调用next()；
 */
public class IteratorActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterator);

        List<String> strList = new ArrayList<>();
        for (int i=0;i<10;i++){
            strList.add("字符串"+i);
        }

        //一，通过while循环遍历
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()){//问
            String next = iterator.next();//取
            if (next.equals("字符串3")) {
                iterator.remove();//删
            }
            DLog.eLog("取出1："+next);
        }

        //二，通过while循环遍历
        for(Iterator<String> iter = strList.iterator();iter.hasNext();){//问
            String next = iter.next();//取
            if (next.equals("字符串7")) {
                iter.remove();//删
            }
            DLog.eLog("取出2："+next);
        }

        DLog.eLog("最后打印："+strList.toString());
    }
}
