package com.yin.yzjcourse.MultiProcess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.MultiProcess.AndroidMulti.MultiMainActivity;
import com.yin.yzjcourse.MultiProcess.SerializableAndParcelable.UserActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_multi_open, R.id.bt_xuliehua})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_multi_open:
                startActivity(new Intent(this, MultiMainActivity.class));
                break;
            case R.id.bt_xuliehua:
                startActivity(new Intent(this, UserActivity.class));
                break;
        }
    }
}
