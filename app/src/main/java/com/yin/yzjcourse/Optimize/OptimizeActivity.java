package com.yin.yzjcourse.Optimize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptimizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_one, R.id.bt_two,R.id.bt_inner_class,R.id.bt_inner_class_two,
            R.id.bt_handler,R.id.bt_handler_solve,R.id.bt_listener,R.id.bt_allocation,
    R.id.bt_clip,R.id.bt_object_pool,R.id.bt_over_draw,R.id.bt_batch_cache,R.id.bt_ui_block})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
                startActivity(new Intent(this,OptimizeOneActivity.class));
                break;
            case R.id.bt_two:
                startActivity(new Intent(this,OptimizeTwoActivity.class));
                break;
            case R.id.bt_inner_class:
                startActivity(new Intent(this,OptimizeInnerClassActivity.class));
                break;
            case R.id.bt_inner_class_two:
                startActivity(new Intent(this,OptimizeInnerClassTwoActivity.class));
                break;
            case R.id.bt_handler:
                startActivity(new Intent(this,OptimizeHandlerActivity.class));
                break;
            case R.id.bt_handler_solve:
                startActivity(new Intent(this,OptimizeHandlerSolveActivity.class));
                break;
            case R.id.bt_listener:
                startActivity(new Intent(this,RemoveListenerActivity.class));
                break;
            case R.id.bt_allocation:
                startActivity(new Intent(this,AllocationTrackActivity.class));
                break;
            case R.id.bt_clip:
                startActivity(new Intent(this,ClipUsageActivity.class));
                break;
            case R.id.bt_over_draw:
                startActivity(new Intent(this,ChatumLatinumActivity.class));
                break;
            case R.id.bt_object_pool:
                startActivity(new Intent(this,ObjectPoolActivity.class));
                break;
            case R.id.bt_batch_cache:
                startActivity(new Intent(this,CachingActivity.class));
                break;
            case R.id.bt_ui_block:
                startActivity(new Intent(this,BusyUIThreadActivity.class));
                break;
        }
    }
}
