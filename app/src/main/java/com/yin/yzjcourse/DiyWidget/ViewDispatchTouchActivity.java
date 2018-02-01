package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 参考XMind:Activity触摸事件
 */
public class ViewDispatchTouchActivity extends BaseActivity{

    @BindView(R.id.my_btn)
    TouchButton myBtn;
    @BindView(R.id.mylayout)
    TouchLinearLayout mylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //试试
        setContentView(R.layout.activity_view_dispatch_touch);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        DLog.eLog("Activity的dispatchTouchEvent-- action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        DLog.eLog("Activity的onUserInteraction");
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        DLog.eLog("Activity的onTouchEvent-- action=" + event.getAction()+",result="+result);
        return result;
    }
}
