package com.yin.yzjcourse.OfficialWeight.RCV;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_base_use, R.id.bt_diff_item,R.id.bt_grid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_base_use:
                startActivity(new Intent(this, RecyclerBaseUseActivity.class));
                break;
            case R.id.bt_diff_item:
                startActivity(new Intent(this, RecyclerDiffItemActivity.class));
                break;
            case R.id.bt_grid:
                startActivity(new Intent(this, RecyclerGridActivity.class));
                break;
        }
    }
}
