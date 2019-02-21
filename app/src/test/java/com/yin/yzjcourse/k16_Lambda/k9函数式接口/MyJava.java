package com.yin.yzjcourse.k16_Lambda.k9函数式接口;

import android.view.View;

import com.yin.yzjcourse.k5类和对象.k1_2继承.MyExtend;
import com.yin.yzjcourse.k6接口.k1接口函数.ClickListener;

/**
 * 函数式接口(SAM)是针对java的，即java中只有一个抽象方法的接口
 */
public class MyJava {
    public void setRun(Runnable runnable){
        runnable.run();
    }

    //如果有重载的方法，则会报错，因此如果有重载的方法，我们还是用匿名类的方法为好
//    public void setRun(MyClick click){
//        click.onClick();
//    }
}
