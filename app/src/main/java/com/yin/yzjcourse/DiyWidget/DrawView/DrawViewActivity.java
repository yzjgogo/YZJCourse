package com.yin.yzjcourse.DiyWidget.DrawView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_bse)
    Button tvBse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_bse)
    public void onClick() {
        startActivity(new Intent(this, BezierLineActivity.class));
    }
}
