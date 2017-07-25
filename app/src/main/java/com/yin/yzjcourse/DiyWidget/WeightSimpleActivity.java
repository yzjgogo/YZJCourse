package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeightSimpleActivity extends BaseActivity {

    @BindView(R.id.sv_shadow_view)
    ShadowView svShadowView;
    @BindView(R.id.tv_shadow)
    TextView tvShadow;
    @BindView(R.id.bm_filter)
    BlurMaskFilterView bmFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_simple);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_radius_add, R.id.bt_x_add, R.id.bt_y_add, R.id.bt_reset_shadow, R.id.bt_clear_radius, R.id.bt_clear_layer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_radius_add:
                svShadowView.addRadius();
                break;
            case R.id.bt_x_add:
                svShadowView.addDx();
                break;
            case R.id.bt_y_add:
                svShadowView.addDy();
                break;
            case R.id.bt_reset_shadow:
                svShadowView.reset();
                break;
            case R.id.bt_clear_radius:
                svShadowView.clearShadowWithRadius();
                break;
            case R.id.bt_clear_layer:
                svShadowView.clearLayer();
                break;
        }
    }

    @OnClick({R.id.bt_inner_light, R.id.bt_solid_light, R.id.bt_normal_light, R.id.bt_outer_light})
    public void onClickFilter(View view) {
        switch (view.getId()) {
            case R.id.bt_inner_light:
                bmFilter.setInner();
                break;
            case R.id.bt_solid_light:
                bmFilter.setSolid();
                break;
            case R.id.bt_normal_light:
                bmFilter.setNormal();
                break;
            case R.id.bt_outer_light:
                bmFilter.setOuter();
                break;
        }
    }
}
