package com.yin.yzjcourse.MaterialDesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.R;

public class SimpleBottomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_bottom_dialog);
        findViewById(R.id.bt_show_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleBottomDialog dialog = SimpleBottomDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"dialog");
            }
        });
    }
}
