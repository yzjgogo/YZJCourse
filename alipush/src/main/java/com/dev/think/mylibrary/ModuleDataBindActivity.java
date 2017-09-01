package com.dev.think.mylibrary;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.think.mylibrary.databinding.ActivityModuleDataBindBinding;

public class ModuleDataBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModuleDataBindBinding bindBinding = DataBindingUtil.setContentView(this,R.layout.activity_module_data_bind);
        DataUser user = new DataUser("module_first", "module_last");
        bindBinding.setDataUser(user);
    }
}
