package com.yin.yzjcourse.DataBindP;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.databinding.ActivityDataBindBinding;
import com.yin.yzjcourse.utils.DLog;

public class DataBindActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        UserPri user = new UserPri("Test2", "User2");
        binding.setUser(user);
        binding.setHandlers(new MyHandlers());
    }

    /**
     * 这个类必须是public
     */
    public class MyHandlers {
        public void onClickFriend(View view) {
            Utils.showToast(DataBindActivity.this,"真的点击了");
            DLog.eLog("真的点击了");
        }
    }
}
