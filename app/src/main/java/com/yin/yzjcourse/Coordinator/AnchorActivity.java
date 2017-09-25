package com.yin.yzjcourse.Coordinator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class AnchorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ioexample_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
