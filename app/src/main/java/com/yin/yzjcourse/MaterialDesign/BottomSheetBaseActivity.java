package com.yin.yzjcourse.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetBaseActivity extends BaseActivity {

    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.sv_bottom)
    NestedScrollView svBottom;
    private BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_base);
        ButterKnife.bind(this);
        sheetBehavior = BottomSheetBehavior.from(svBottom);
    }

    @OnClick({R.id.bt_expanded, R.id.bt_hide, R.id.bt_collapsed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_expanded:
                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
//                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                Utils.showToast(BottomSheetBaseActivity.this,"展开");
                break;
            case R.id.bt_hide:
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                Utils.showToast(BottomSheetBaseActivity.this,"隐藏");
                break;
            case R.id.bt_collapsed:
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                Utils.showToast(BottomSheetBaseActivity.this,"收缩");
                break;
        }
    }
}
