package com.yin.yzjcourse.Base.mysurface;

import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;

public class SurfaceActivity extends BaseActivity {
    private SurfaceViewTemplate svt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        svt = findViewById(R.id.sv);
        findViewById(R.id.bt_clean).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                svt.reDraw();
            }
        });
    }
}
