package com.yin.yzjcourse.DesignModel;

import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignModelListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_celuo, R.id.bt_guanchazhe, R.id.bt_decorate, R.id.bt_gongchang, R.id.bt_danli, R.id.bt_mingling, R.id.bt_shipeiqi, R.id.bt_waiguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_celuo:
                break;
            case R.id.bt_guanchazhe:
                break;
            case R.id.bt_decorate:
                break;
            case R.id.bt_gongchang:
                break;
            case R.id.bt_danli:
                break;
            case R.id.bt_mingling:
                break;
            case R.id.bt_shipeiqi:
                break;
            case R.id.bt_waiguan:
                break;
        }
    }

//    @OnClick({R.id.bt_decorate, R.id.bt_other})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.bt_decorate:
//                startActivity(new Intent(this,DecoratorPatternActivity.class));
//                break;
//            case R.id.bt_other:
//                break;
//        }
//    }
}
