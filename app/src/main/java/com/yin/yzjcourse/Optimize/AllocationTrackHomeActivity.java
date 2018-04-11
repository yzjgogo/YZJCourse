package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;

public class AllocationTrackHomeActivity extends BaseActivity {
    private ArrayList<User> mUserList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_track_home);
        createObject();
    }

    /**
        用于测试Android Device Monitor中Allocation Tracker的使用：用于分析一段时间中内存的去向，都分配给了谁，在哪分配的
        参考图片：allocation_tracker_usage.png
     */
    private void createObject() {
        mUserList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("殷" + i, 18 + i);
            mUserList.add(user);
        }
    }
}
