package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetSizeActivity extends BaseActivity {


    @BindView(R.id.wrap_two)
    FrameLayout wrapTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_size);
        ButterKnife.bind(this);

        int w3 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h3 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        wrapTwo.measure(w3, h3);
        DLog.eLog("宽高wrap：" + wrapTwo.getMeasuredWidth() + "," + wrapTwo.getMeasuredHeight());
    }
}
