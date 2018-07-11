package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiyMenuActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_menu);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        toolbar.setTitle("我是标题");
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.head_flavors, menu);
        final MenuItem item = menu.findItem(R.id.flavor);
//        getActionView()返回你自定义的菜单布局，设置单击事件的目的是，让其单击时执行onOptionsItemSelected，从而只需统一在onOptionsItemSelected处理即可
        item.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(item);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.flavor:
                Utils.showToast(this, "菜单被点击了");
//                Utils.startActivity(this, FavoritesActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
