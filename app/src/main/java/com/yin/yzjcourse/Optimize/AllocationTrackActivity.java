package com.yin.yzjcourse.Optimize;

import android.content.Intent;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllocationTrackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_track);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        startActivity(new Intent(this,AllocationTrackHomeActivity.class));
    }
}
