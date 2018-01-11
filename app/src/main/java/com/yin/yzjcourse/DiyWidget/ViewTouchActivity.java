package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewTouchActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    @BindView(R.id.my_btn)
    Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);
        ButterKnife.bind(this);

        myBtn.setOnTouchListener(this);

        myBtn.setOnClickListener(this);
    }
    /**
     * 1:单击Button:多次调用onTouch
     * OnTouchListener--onTouch-- action=0 --android.support.v7.widget.AppCompatButton > ACTION_DOWN
     * OnTouchListener--onTouch-- action=1 --android.support.v7.widget.AppCompatButton > ACTION_UP
     * OnClickListener--onClick--android.support.v7.widget.AppCompatButton > onClick在ACTION_UP之后执行
     *
     * 2：手指在Button上滑动：多次调用onTouch
     * OnTouchListener--onTouch-- action=0 --android.support.v7.widget.AppCompatButton > ACTION_DOWN
     * OnTouchListener--onTouch-- action=2 --android.support.v7.widget.AppCompatButton > ACTION_MOVE
     *                                      .
     *                                      .
     *                                      .
     * OnTouchListener--onTouch-- action=2 --android.support.v7.widget.AppCompatButton > ACTION_MOVE
     * OnTouchListener--onTouch-- action=1 --android.support.v7.widget.AppCompatButton > ACTION_UP
     * OnClickListener--onClick--android.support.v7.widget.AppCompatButton > onClick在ACTION_UP之后执行
     *
     * 3：修改onTouch的返回值为true：再单击或在Button上滑动，多次调用onTouch
     * OnTouchListener--onTouch-- action=0 --android.support.v7.widget.AppCompatButton > ACTION_DOWN
     * OnTouchListener--onTouch-- action=1 --android.support.v7.widget.AppCompatButton > ACTION_UP
     * 不再执行onClick
     *
     * 总结：
     * 1：Android控件的触摸事件触发顺序是先触发onTouch，其次onClick。
     * 2：如果控件的onTouch返回true将会阻止事件继续传递(onTouch中已经将触摸事件消费掉了)，返回false事件会继续传递(onTouch并没有消费掉事件)。
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("yin", "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.i("yin", "OnClickListener--onClick--" + v);
    }
}
