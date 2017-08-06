package com.yin.yzjcourse.DataBindP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataBindSimplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bind_simples);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_activity_db, R.id.bt_fragment_db})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_db:
                startActivity(new Intent(this, DataBindActivity.class));
                break;
            case R.id.bt_fragment_db:
                startActivity(new Intent(this, DataBindFragmentActivity.class));
                break;
        }
    }
}
