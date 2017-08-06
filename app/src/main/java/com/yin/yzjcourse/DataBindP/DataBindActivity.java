package com.yin.yzjcourse.DataBindP;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        User user = new User("Test", "User");
        binding.setUser(user);
    }
}
