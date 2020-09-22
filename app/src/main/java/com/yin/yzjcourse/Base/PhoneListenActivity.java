package com.yin.yzjcourse.Base;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;

public class PhoneListenActivity extends BaseActivity {

    //    @BindView(R.id.iv_img)
//    ImageView ivImg;
//    @BindView(R.id.tv_content)
//    TextView tvContent;
    MyPhoneStateListener myPhoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_listen);
        ButterKnife.bind(this);
        myPhoneStateListener = new MyPhoneStateListener();
        telephony();
    }

    private void telephony() {
        //获得相应的系统服务
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            try {
                tm.listen(myPhoneStateListener, MyPhoneStateListener.LISTEN_CALL_STATE);
            } catch (Exception e) {
                e.printStackTrace();
                // 异常捕捉
                DLog.eLog("捕获到异常：" + e.getMessage());
            }
        }
    }

    static class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    DLog.eLog("电话挂断...");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    DLog.eLog("正在通话...");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    DLog.eLog("电话响铃...");
                    break;
            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }
}
