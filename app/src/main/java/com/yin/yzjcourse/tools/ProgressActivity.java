package com.yin.yzjcourse.tools;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 参考图片：progress_demo.jpg
 * progressbar_max.xml
 */
public class ProgressActivity extends BaseActivity {
    @BindView(R.id.progress_loading)
    ProgressBar progress_loading;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                countDownTimer = new CountDownTimer(100 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long residueSec = millisUntilFinished / 1000;
                        int progress = (int) (100-residueSec);
                        DLog.eLog("当前进度："+progress);
                        //模拟播放进度
                        progress_loading.setProgress(progress);
                        //模拟缓冲进度，比播放进度快一点，因此+，如果减，就看不到SecondaryProgress
                        progress_loading.setSecondaryProgress(progress+5);
                    }

                    @Override
                    public void onFinish() {
                        progress_loading.setProgress(100);
                    }
                };
                countDownTimer.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (countDownTimer!=null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }
}
