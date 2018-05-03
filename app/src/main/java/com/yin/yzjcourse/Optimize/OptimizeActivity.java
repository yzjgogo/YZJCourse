package com.yin.yzjcourse.Optimize;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

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
    R.id.bt_clip,R.id.bt_object_pool,R.id.bt_over_draw,R.id.bt_batch_cache,R.id.bt_ui_block,
    R.id.bt_trace_view,R.id.bt_structure})
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
            case R.id.bt_trace_view:
                Debug.startMethodTracing("custom");
                startTrace();
                Debug.stopMethodTracing();
                Utils.showToast(this, "成功");
                break;
            case R.id.bt_batch_cache:
                startActivity(new Intent(this,CachingActivity.class));
                break;
            case R.id.bt_ui_block:
                startActivity(new Intent(this,BusyUIThreadActivity.class));
                break;
            case R.id.bt_structure:
                startActivity(new Intent(this,DataStructuresActivity.class));
                break;
        }
    }

    /**
     * jie1()和jie2()没有调用关系是兄弟关系
     */
    private void startTrace() {
        jie1();
        jie2();
    }

    /**
     * jie2()中两次调用jie3()，其中jie3(0)直接return，不产生递归也不会调用jie4()
     * jie3(3)会先调用一次jie4()再产生3次递归调用
     */
    private void jie2() {
        jie3(0);
        jie3(3);
    }

    private void jie3(int count) {
        if (count == 3) {
            jie4();
        }
        if (count == 0) {
            return;
        } else {
            jie3(count - 1);
        }
    }

    /**
     * 故意做比较耗时的操作：用于区分Excl和Incl的关系
     */
    private void jie4() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int k = i + j;
            }
        }
    }

    private void jie1() {

    }
}
