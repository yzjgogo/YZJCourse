package com.yin.yzjcourse.Coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.Coordinator.designDemo.CheeseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoordinatorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_office_demo, R.id.bt_place,R.id.bt_listener,R.id.bt_concept})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_office_demo:
                startActivity(new Intent(this, CheeseActivity.class));
                break;
            case R.id.bt_place:
                startActivity(new Intent(this, AnchorActivity.class));
                break;
            case R.id.bt_listener:
                startActivity(new Intent(this, FlexibleSpaceExampleActivity.class));
                break;
            case R.id.bt_concept:
                startActivity(new Intent(this, MaterialUpConceptActivity.class));
                break;
        }
    }
}
