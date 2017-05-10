package com.yin.yzjcourse.DiyWidget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yin.yzjcourse.DiyWidget.PieGraph.PieGraph;
import com.yin.yzjcourse.DiyWidget.PieGraph.PieSlice;
import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/5/8.
 */

public class DiyViewActivity extends AppCompatActivity {
    private PieGraph pieOne;
    private PieGraph pieMore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);
        pieOne = (PieGraph) findViewById(R.id.pie_one);
        pieMore = (PieGraph) findViewById(R.id.pie_more);
        initView();
    }

    private void initView() {
        pieOne.addSlice(new PieSlice(Color.BLUE,5));
        pieMore.addSlice(new PieSlice(Color.CYAN,2));
        pieMore.addSlice(new PieSlice(Color.MAGENTA,4));
        pieMore.addSlice(new PieSlice(Color.RED,6));
    }
}