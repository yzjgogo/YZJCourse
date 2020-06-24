package com.yin.yzjcourse.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import java.util.StringTokenizer;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StrAboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_str_about);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_str_split})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_str_split:
                splitStr();
                break;
        }
    }

    /**
     * StringTokenizer用于分割字符串
     * hasMoreElements()和hasMoreTokens()一样，进内部去看，其实hasMoreElements()调用了hasMoreTokens()
     * nextElement()和nextToken()一样，进内部去看，其实nextElement()调用了nextToken()，只是nextElement()返回Object类型。
     */
    private void splitStr() {
        String sentence = "What does she want to do when she's older? ";
        StringTokenizer st = new StringTokenizer(sentence, " ");
//        while (st.hasMoreTokens()){
        while (st.hasMoreElements()){
            String next = st.nextToken();
//            String next = (String) st.nextElement();
            DLog.eLog("分割："+next);
        }
    }
}
