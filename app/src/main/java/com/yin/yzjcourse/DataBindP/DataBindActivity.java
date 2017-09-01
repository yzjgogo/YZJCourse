package com.yin.yzjcourse.DataBindP;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.databinding.ActivityDataBindBinding;

public class DataBindActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        UserPri user = new UserPri("Test2", "User2");
        binding.setUser(user);
    }
}
