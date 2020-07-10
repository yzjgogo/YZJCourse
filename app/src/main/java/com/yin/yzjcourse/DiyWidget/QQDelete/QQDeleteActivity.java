package com.yin.yzjcourse.DiyWidget.QQDelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * http://www.jianshu.com/p/9f0736c24029
 *
 * https://github.com/crazyqiang/AndroidStudy
 *
 * 要想将小红点拖拽到父View之外需要用到setDrawingCacheEnabled(),参考：ViewCacheActivity
 */
public class QQDeleteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqdelete);
    }
}
