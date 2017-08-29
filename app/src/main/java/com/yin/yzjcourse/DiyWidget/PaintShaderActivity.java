package com.yin.yzjcourse.DiyWidget;

import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaintShaderActivity extends BaseActivity {

    @BindView(R.id.shader_view)
    ShaderView shaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_shader);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_repeat_repeat, R.id.bt_clamp_clamp, R.id.bt_mirror_mirror, R.id.bt_repeat_clamp, R.id.bt_repeat_mirror, R.id.bt_clamp_repeat, R.id.bt_clamp_mirror, R.id.bt_mirror_repeat, R.id.bt_mirror_clamp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_repeat_repeat:
                shaderView.setTileMode(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                break;
            case R.id.bt_clamp_clamp:
                shaderView.setTileMode(Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                break;
            case R.id.bt_mirror_mirror:
                shaderView.setTileMode(Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
                break;
            case R.id.bt_repeat_clamp:
                shaderView.setTileMode(Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
                break;
            case R.id.bt_repeat_mirror:
                shaderView.setTileMode(Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
                break;
            case R.id.bt_clamp_repeat:
                shaderView.setTileMode(Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
                break;
            case R.id.bt_clamp_mirror:
                shaderView.setTileMode(Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
                break;
            case R.id.bt_mirror_repeat:
                shaderView.setTileMode(Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
                break;
            case R.id.bt_mirror_clamp:
                shaderView.setTileMode(Shader.TileMode.MIRROR, Shader.TileMode.CLAMP);
                break;
        }
    }
}
