package com.yin.yzjcourse.Jetpack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.Jetpack.Room.RoomActivity;
import com.yin.yzjcourse.Jetpack.work.WorkActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JetpackHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_lifecycle, R.id.bt_activity_lifecycle, R.id.bt_service_lifecycle,
            R.id.bt_app_lifecycle, R.id.bt_nav, R.id.bt_view_model, R.id.bt_live_data,
            R.id.bt_room, R.id.bt_work_manager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_lifecycle:
                startActivity(new Intent(this, MyLCActivity.class));
                break;
            case R.id.bt_activity_lifecycle:
                startActivity(new Intent(this, PageLifecycleActivity.class));
                break;
            case R.id.bt_service_lifecycle:
                startActivity(new Intent(this, ServiceLifecycleActivity.class));
                break;
            case R.id.bt_app_lifecycle:
                //看AppLocationListener
                break;
            case R.id.bt_nav:
                startActivity(new Intent(this, NavigationActivity.class));
                break;
            case R.id.bt_view_model:
                startActivity(new Intent(this, ViewModelActivity.class));
                break;
            case R.id.bt_live_data:
                startActivity(new Intent(this, LiveDataActivity.class));
                break;
            case R.id.bt_room:
                startActivity(new Intent(this, RoomActivity.class));
                break;
            case R.id.bt_work_manager:
                startActivity(new Intent(this, WorkActivity.class));
                break;
        }
    }
}
