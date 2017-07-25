package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DiyWidget.PieGraph.PieGraph;
import com.yin.yzjcourse.DiyWidget.PieGraph.PieSlice;
import com.yin.yzjcourse.DiyWidget.QQDelete.QQDeleteActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by think on 2017/5/8.
 */

public class DiyViewActivity extends BaseActivity {
    private PieGraph pieOne;
    private PieGraph pieMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);
        ButterKnife.bind(this);
        pieOne = (PieGraph) findViewById(R.id.pie_one);
        pieMore = (PieGraph) findViewById(R.id.pie_more);
        initView();
    }

    private void initView() {
        pieOne.addSlice(new PieSlice(Color.BLUE, 5));
        pieMore.addSlice(new PieSlice(Color.CYAN, 2));
        pieMore.addSlice(new PieSlice(Color.MAGENTA, 4));
        pieMore.addSlice(new PieSlice(Color.RED, 6));
    }

    @OnClick({R.id.bt_src_out_eraser,R.id.bt_diy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_src_out_eraser:
                Intent intent = new Intent(this,XfermodeSrcOutEraserActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_diy:
                Intent intent1 = new Intent(this,DiySimpleActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
