package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScaleDoubleFingerActivity extends BaseActivity{

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.fl_content)
    MyFrameGroup fl_content;
    @BindView(R.id.bt_in_group)
    MyButtonInGroup bt_in_group;
    @BindView(R.id.iv_in_group)
    MyImageViewInGroup iv_in_group;
    @BindView(R.id.rl_in_group)
    MyRelativeInGroup rl_in_group;

    float mCurrentScale = 1;
    float last_x = -1;
    float last_y = -1;
    float preDistance;
    int lastType;
    int transCount;
    int scaleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //试试
        setContentView(R.layout.activity_scale_double_finger);
        ButterKnife.bind(this);
//        iv_in_group.setClickable(true);
//        iv_in_group.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//        rl_in_group.setClickable(true);
        fl_content.setOnTouchListener(new View.OnTouchListener() {
            float baseValue;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    DLog.eLog("按下");
                    baseValue = 0;
                    float x = last_x = event.getRawX();
                    float y = last_y = event.getRawY();



//                    preDistance = 0;
//                    float x1 = event.getX(0) - event.getX(1);
//                    float y1 = event.getY(0) - event.getY(1);
//                    preDistance = (float) Math.sqrt(x1 * x1 + y1 * y1);// 计算两点的距离
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (event.getPointerCount() == 2) {
                        if (lastType == 2) {
                            DLog.eLog("缩放:"+event.getEventTime());
                            float x = event.getX(0) - event.getX(1);
                            float y = event.getY(0) - event.getY(1);
                            float value = (float) Math.sqrt(x * x + y * y);// 计算两点的距离
                            if (baseValue == 0) {
                                baseValue = value;
                            } else {
                                if (value - baseValue >= 10 || value - baseValue <= -10) {
                                    float scale = value / baseValue;// 当前两点间的距离除以手指落下时两点间的距离就是需要缩放的比例。
//                                DLog.eLog("缩放比："+scale);
                                    fl_content.setScaleX(fl_content.getScaleX()*scale);  //缩放图片
                                    fl_content.setScaleY(fl_content.getScaleY()*scale);  //缩放图片
                                }
                            }
                        }
                        if (lastType != 2) {
                            lastType = 2;
                        }
                    }
                    else if (event.getPointerCount() == 1) {
                        if (lastType == 1 && transCount>5) {
                            DLog.eLog("位移："+event.getEventTime());
                            float x = event.getRawX();
                            float y = event.getRawY();
                            x -= last_x;
                            y -= last_y;
                            if (x >= 10 || y >= 10 || x <= -10 || y <= -10)
//                            img_transport(x, y); //移动图片位置
                                fl_content.setTranslationX(fl_content.getTranslationX()+x);
                            fl_content.setTranslationY(fl_content.getTranslationY()+y);
                            last_x = event.getRawX();
                            last_y = event.getRawY();
                        }
                        if (lastType!=1) {
                            lastType = 1;
                            transCount=0;
                        }
                        transCount++;
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return true;
            }
        });
//        tv.setFocusable(true);
//        tv.setClickable(true);
//        tv.setLongClickable(true);
    }
}
