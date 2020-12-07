package com.yin.yzjcourse.structure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.Jetpack.MyLCActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStructureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structure);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_array_list, R.id.bt_linked_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_array_list:
                ArrayListY arrayListY = new ArrayListY();
                arrayListY.add("a");
                arrayListY.add("b");
                arrayListY.add("c");
                arrayListY.add("d");
                DLog.eLog("输出："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));
                arrayListY.remove(2);
                DLog.eLog("输出2："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));


                break;
            case R.id.bt_linked_list:
                break;
        }
    }
}
