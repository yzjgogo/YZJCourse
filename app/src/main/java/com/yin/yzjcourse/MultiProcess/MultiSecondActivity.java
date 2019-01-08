package com.yin.yzjcourse.MultiProcess;

import android.os.Bundle;
import android.os.Process;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiSecondActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_second);
        ButterKnife.bind(this);
        tv.setText(Process.myPid() + "\n" + ProcessUtils.getProcessName(this)+"\n"+Process.myUid()+"\n"+
                ProcessUtils.mStatus);
    }
}
