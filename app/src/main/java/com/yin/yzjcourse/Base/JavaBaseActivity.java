package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JavaBaseActivity extends BaseActivity {
//    @BindView(R.id.tv_content1)
//    TextView tvContent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_base);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_math,R.id.tv_array})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_math:

                break;
            case R.id.tv_array:
                doArraysCopyOf();
                doSystemArrayCopy();
                break;
        }
    }

    private void doArraysCopyOf(){
        String[] strArr = {"a","b"};
        String[] newStrArr = Arrays.copyOf(strArr,2);
        //数组：Arrays.copyOf(strArr,1) -> [a] , false , false
        //数组：Arrays.copyOf(strArr,2) -> [a, b] , true , false
        //数组：Arrays.copyOf(strArr,3) -> [a, b, null] , false , false
        System.out.println("数组："+Arrays.toString(newStrArr) +" , "+Arrays.equals(newStrArr,strArr) +" , "+(newStrArr == strArr));
    }

    /**
     * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * src：被copy的原数组；
     * srcPos：从原数组的srcPos位置的元素开始逐个copy；
     * dest：要copy到的目标数组；
     * destPos：目标数组从第destPos个index开始接收copy的元素
     * length：src从开始copy的srcPos开始，一共要copy共length个元素
     */
    private void doSystemArrayCopy(){
        int[] configSpec = new int[]{10, 11, 12, 13, 14, 15};
        final int len = configSpec.length;
        final int[] newConfigSpec = new int[len + 2];
        System.arraycopy(configSpec, 2, newConfigSpec, 4, 3);
        //输出：[10, 11, 12, 13, 14, 15],[0, 0, 0, 0, 12, 13, 14, 0]
        System.out.println("输出：" + Arrays.toString(configSpec) + "," + Arrays.toString(newConfigSpec));


    }
}
