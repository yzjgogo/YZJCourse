package com.yin.yzjcourse.DiyWidget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewHitRectActivity extends BaseActivity {

    @BindView(R.id.testView)
    TextView testView;
    @BindView(R.id.sv)
    ScrollView sv;

    /**
     * getHitRect()返回当前view相对父View的坐标矩形区域，即父View的左上角为坐标系的(0,0)
     * 针对可滚动的控件超出屏幕也不影响计算坐标，即坐标系可以超出屏幕的宽高
     * 当前View如果是invisible不影响,如果是gone则不行。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit_rect);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_sv, R.id.bt_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sv:
//                DLog.eLog("单击sv");
//                sv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        DLog.eLog("单击sv2");
//                        sv.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                        Rect rect = new Rect();
                        sv.getHitRect(rect);

                        DLog.eLog("ScrollView--left:  " + rect.left + "--top: " + rect.top + "--right: " + rect.right + "--bottom: " + rect.bottom);

//                    }
//                });
                break;
            case R.id.bt_view:
//                DLog.eLog("单击view");
//                testView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        DLog.eLog("单击view2");
//                        testView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                        Rect rect1 = new Rect();
                        testView.getHitRect(rect1);

                        DLog.eLog("View--left:  " + rect1.left + "--top: " + rect1.top + "--right: " + rect1.right + "--bottom: " + rect1.bottom);

//                    }
//                });
                break;
        }
    }
}
