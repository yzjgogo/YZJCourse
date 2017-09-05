package com.yin.yzjcourse.MaterialDesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomNavigationActivity extends BaseActivity {

    @BindView(R.id.bottom)
    BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);
        listenerSelection();
    }

    /**
     * 监听item的选中状态
     */
    private void listenerSelection() {
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.favorites) {
                    Utils.showToast(BottomNavigationActivity.this, "第二被选中");
                }
                return true;//要return true否则点击各个item就没法选中了
            }
        });
        //用lambda表达式
        /*
        bottom.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.favorites) {
                DLog.eLog("第二被选中");
                return true;
            }
            return false;
        });
        */
    }

    @OnClick(R.id.bt_select)
    public void onClick() {
        bottom.getMenu().getItem(1).setChecked(true);
    }

    /**
     * 官方没有提供获取选中的item的方法，需要自己遍历
     */
    @OnClick(R.id.bt_get_selection)
    public void onGetSelection() {
        int selectionIndex = 0;
        Menu menu = bottom.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked()) {
                selectionIndex = i;
            }
        }
        selectionIndex = 0;
        Utils.showToast(this, "选中了第" + (selectionIndex + 1) + "个");
    }
}
