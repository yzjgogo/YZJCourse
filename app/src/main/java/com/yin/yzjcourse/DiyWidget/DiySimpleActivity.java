package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DiyWidget.QQDelete.QQDeleteActivity;
import com.yin.yzjcourse.PhotoTag.MainAct;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiySimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_simple);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_qq_delete, R.id.bt_shade,R.id.bt_measure_layout,
            R.id.bt_measure_layout_margin,R.id.bt_flow,R.id.bt_shader,R.id.bt_shader_telescope,
    R.id.bt_shader_avatar,R.id.bt_shape,R.id.bt_level_list,R.id.bt_photo_tag})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_qq_delete:
                startActivity(new Intent(this, QQDeleteActivity.class));
                break;
            case R.id.bt_shade:
                startActivity(new Intent(this, WeightSimpleActivity.class));
                break;
            case R.id.bt_measure_layout:
                startActivity(new Intent(this, MyLinActivity.class));
                break;
            case R.id.bt_measure_layout_margin:
                startActivity(new Intent(this, MyMarginLinActivity.class));
                break;
            case R.id.bt_flow:
                startActivity(new Intent(this, FlowActivity.class));
                break;
            case R.id.bt_shader:
                startActivity(new Intent(this, PaintShaderActivity.class));
                break;
            case R.id.bt_shader_telescope:
                startActivity(new Intent(this, TelescopeShaderActivity.class));
                break;
            case R.id.bt_shader_avatar:
                startActivity(new Intent(this, ShaderAvatarActivity.class));
                break;
            case R.id.bt_shape:
                startActivity(new Intent(this, ShapeUsageActivity.class));
                break;
            case R.id.bt_level_list:
                startActivity(new Intent(this, LevelListActivity.class));
                break;
            case R.id.bt_photo_tag:
                startActivity(new Intent(this, MainAct.class));
                break;
        }
    }
}