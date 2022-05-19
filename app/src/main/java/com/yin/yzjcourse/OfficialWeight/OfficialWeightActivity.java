package com.yin.yzjcourse.OfficialWeight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.OfficialWeight.RCV.RecyclerHomeActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfficialWeightActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offical_weight);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_auto_complete, R.id.ll_root,R.id.bt_custom_auto_complete,R.id.bt_recycler,R.id.bt_et_filter,R.id.bt_fbl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_root:
                break;
            case R.id.bt_auto_complete:
                startActivity(new Intent(this, AutoCompleteActivity.class));
                break;
            case R.id.bt_custom_auto_complete:
                startActivity(new Intent(this, CustomAutoCompleteTextViewActivity.class));
                break;
            case R.id.bt_recycler:
                startActivity(new Intent(this, RecyclerHomeActivity.class));
                break;
            case R.id.bt_et_filter:
                startActivity(new Intent(this, EtFilterActivity.class));
                break;
            case R.id.bt_fbl:
                startActivity(new Intent(this, FlexBoxActivity.class));
                break;
        }
    }
}
