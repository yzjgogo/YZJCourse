package com.yin.yzjcourse.MaterialDesign;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipChildrenActivity extends BaseActivity {


    @BindView(R.id.view_clip)
    TextView viewClip;
    @BindView(R.id.ll_test_original)
    LinearLayout llTestOriginal;
    @BindView(R.id.view_test3)
    TextView viewTest3;
    @BindView(R.id.ll_test2)
    LinearLayout llTest2;
    @BindView(R.id.ll_test)
    LinearLayout llTest;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        ButterKnife.bind(this);
    }
}
