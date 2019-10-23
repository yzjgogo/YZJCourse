package com.yin.yzjcourse.Base;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SvScrollActivity extends BaseActivity {

    @BindView(R.id.sv)
    ScrollView sv;

    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv_scroll);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_to_bottom, R.id.bt_to_top,R.id.bt_scroll_to,R.id.bt_scroll_by,R.id.bt_anim_to_bottom,R.id.bt_anim_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_to_bottom:
                sv.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.bt_to_top:
                sv.fullScroll(ScrollView.FOCUS_UP);
                break;
            case R.id.bt_scroll_to:
                sv.scrollTo(0,500);//从(0,0)滚动到(0,500)  即沿y轴向下(+)滚动500px，如果向上滚动则为负数
                break;
            case R.id.bt_scroll_by:
                sv.scrollTo(0,0);//还原初始位置
                //先向下滚动200px
                sv.scrollBy(0,200);
                //再在(0,200)的基础上再向下滚动300px
                sv.scrollBy(0,300);
                //结果就是等价于sv.scrollTo(0,500);
                break;
            case R.id.bt_anim_to_bottom:
                //从顶部滚动到底部
                sv.post(new Runnable() {
                    @Override
                    public void run() {
                        int llh = ll.getMeasuredHeight();//ScrollView的第一个子元素的高肯定大于或等于ScrollView的高，因为这个高包括屏幕之外的所有内容的高
                        int svh = sv.getMeasuredHeight();
                        startScrollAnim(0,llh-svh);
                    }
                });
                break;
            case R.id.bt_anim_to_top:
                //从底部滚动到顶部
                sv.post(new Runnable() {
                    @Override
                    public void run() {
                        int llh = ll.getMeasuredHeight();
                        int svh = sv.getMeasuredHeight();
                        startScrollAnim(-(llh-svh),0);
                    }
                });
                break;
        }
    }

    private void startScrollAnim(int startValue,int endValue){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startValue, endValue);
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();//获取ValueAnimator当前的计算点
                DLog.eLog("值变化："+curValue);
                sv.scrollTo(0,Math.abs(curValue));
            }
        });
        valueAnimator.start();
    }
}
