package com.yin.yzjcourse.MaterialDesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class BottomSheetExampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.sl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetExampleActivity.this, NestedScrollViewActivity.class));
            }
        });

        findViewById(R.id.rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetExampleActivity.this, RecyclerViewActivity.class));
            }
        });

        findViewById(R.id.bs_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetExampleActivity.this, BottomSheetDialogActivity.class));
            }
        });

        findViewById(R.id.beautiful).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetExampleActivity.this, BeautifulBottomSheetActivity.class));
            }
        });

    }
}
