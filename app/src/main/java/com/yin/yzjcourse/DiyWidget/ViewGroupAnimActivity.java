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

    /**
     * 这部分是用xml补间动画实现viewGroup创建时的动画
     * @param view
     */
    @OnClick({R.id.bt_layout_xml, R.id.bt_layout_code, R.id.bt_grid_xml, R.id.bt_grid_code})
    public void onClickCreate(View view) {
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

    /**
     * 这部分是用ObjectAnimator实现viewGroup创建后添加或删除元素的动画
     * @param view
     */
    @OnClick({R.id.bt_change_anim, R.id.bt_transition})
    public void onClickAddOrRemove(View view) {
        switch (view.getId()) {
            case R.id.bt_change_anim:
                startActivity(new Intent(this, AnimateLayoutChangesActivity.class));
                break;
            case R.id.bt_transition:
                startActivity(new Intent(this, LayoutTransitionActivity.class));
                break;
        }
    }
}
