package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewGroupAnimActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_anim);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_layout_xml, R.id.bt_layout_code, R.id.bt_grid_xml, R.id.bt_grid_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_layout_xml:
                startActivity(new Intent(this, LayoutAnimationXmlActivity.class));
                break;
            case R.id.bt_layout_code:
                startActivity(new Intent(this, LayoutAnimationCodeActivity.class));
                break;
            case R.id.bt_grid_xml:
                startActivity(new Intent(this, GridLayoutAnimationXmlActivity.class));
                break;
            case R.id.bt_grid_code:
                startActivity(new Intent(this, GridLayoutAnimationCodeActivity.class));
                break;
        }
    }
}
