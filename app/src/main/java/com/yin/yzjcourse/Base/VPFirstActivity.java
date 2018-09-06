package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class VPFirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpfirst);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_vp_base, R.id.bt_title_strip,R.id.bt_tab_strip,
            R.id.bt_tab_diy,R.id.bt_fragment,R.id.bt_state_fragment,
            R.id.bt_vp_trans,R.id.bt_tab_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_vp_base:
                startActivity(new Intent(this, VPBaseActivity.class));
                break;
            case R.id.bt_title_strip:
                startActivity(new Intent(this, VPTitleStripActivity.class));
                break;
            case R.id.bt_tab_strip:
                startActivity(new Intent(this, VPTabStripActivity.class));
                break;
            case R.id.bt_tab_diy:
                startActivity(new Intent(this, VPTabDiyActivity.class));
                break;
            case R.id.bt_fragment:
                startActivity(new Intent(this, VPFragmentActivity.class));
                break;
            case R.id.bt_state_fragment:
                startActivity(new Intent(this, VPStateFragmentActivity.class));
                break;
            case R.id.bt_vp_trans:
                startActivity(new Intent(this, VPTransformerActivity.class));
                break;
            case R.id.bt_tab_layout:
                startActivity(new Intent(this, VPTabLayoutActivity.class));
                break;
        }
    }
}
