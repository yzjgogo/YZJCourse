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

public class ViewDrawingRectActivity extends BaseActivity {

    @BindView(R.id.test_view)
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drawing_rect);
        ButterKnife.bind(this);
        testView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                testView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Rect rect = new Rect();
                testView.getDrawingRect(rect);
                //rect.left:  0---rect.top:  0---rect.right:  200---rect.bottom:  200
                DLog.eLog(">>>>>>>>>>>>>>>>rect.left:  " + rect.left + "---rect.top:  " + rect.top + "---rect.right:  " + rect.right + "---rect.bottom:  " + rect.bottom);
            }
        });
    }
}
