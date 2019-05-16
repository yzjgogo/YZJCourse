package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setStatusBarColor(Color.RED);
        }
        setContentView(R.layout.activity_title_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_diy_menu, R.id.bt_status_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_diy_menu:
                startActivity(new Intent(this, DiyMenuActivity.class));
                break;
            case R.id.bt_status_bar:
                Toast.makeText(this,"看当前activity的onCreate方法里的代码即可",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
