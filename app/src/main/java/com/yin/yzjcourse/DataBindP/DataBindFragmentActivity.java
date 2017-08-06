package com.yin.yzjcourse.DataBindP;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.databinding.ActivityDataBindBinding;

public class DataBindFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_data_bind);
    }
}
