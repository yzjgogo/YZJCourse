package com.yin.yzjcourse.MaterialDesign;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        setContentView(R.layout.activity_clip_child);
        ButterKnife.bind(this);
    }
}
