package com.yin.yzjcourse.DiyWidget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHitRectActivity extends BaseActivity {

    @BindView(R.id.testView)
    TextView testView;

    /**
     * getHitRect()返回当前view相对父View的坐标矩形区域，即父View的左上角为坐标系的(0,0)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit_rect);
        ButterKnife.bind(this);
        testView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                testView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                Rect rect = new Rect();
                testView.getHitRect(rect);

                //--left:  125--top: 100--right: 325--bottom: 300
                DLog.eLog("--left:  " + rect.left + "--top: " + rect.top + "--right: " + rect.right + "--bottom: " + rect.bottom);

            }
        });
    }
}
