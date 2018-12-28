package com.yin.yzjcourse.DesignModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DesignModel.Decorate.DecoratorPatternActivity;
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

    @OnClick({R.id.bt_decorate, R.id.bt_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_decorate:
                startActivity(new Intent(this,DecoratorPatternActivity.class));
                break;
            case R.id.bt_other:
                break;
        }
    }
}
