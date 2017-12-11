package com.yin.yzjcourse.MaterialDesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDesignActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_bottom_navigation, R.id.bt_bottom_sheet,
            R.id.bt_expanded_notifications,R.id.bt_button_style,
    R.id.bt_clip_children,R.id.bt_clip_padding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_bottom_navigation:
                startActivity(new Intent(this, BottomNavigationActivity.class));
                break;
            case R.id.bt_bottom_sheet:
//                LinearLayout linearLayout = new LinearLayout(MaterialDesignActivity.this);
//                linearLayout.setClipToPadding(false);
//                linearLayout.setClipChildren(false);
                startActivity(new Intent(this, BottomSheetExampleActivity.class));
                break;
            case R.id.bt_expanded_notifications:
                startActivity(new Intent(this, NotificationsActivity.class));
                break;
            case R.id.bt_button_style:
                startActivity(new Intent(this, ButtonsActivity.class));
                break;
            case R.id.bt_clip_children:
                startActivity(new Intent(this, ClipChildrenActivity.class));
                break;
            case R.id.bt_clip_padding:
                startActivity(new Intent(this, ClipToPaddingActivity.class));
                break;
        }
    }
}
