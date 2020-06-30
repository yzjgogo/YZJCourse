package com.yin.yzjcourse.tools;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import java.util.Timer;
import java.util.TimerTask;

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
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    private CountDownTimer countDownTimer;

    private Timer mTimer;
    private TimerTask mTimerTask;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);
        seekBar.setProgress(0);
        seekBar.setMax(1000*10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //fromUser代表是不是用户拖拽滑块产生的进度
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DLog.eLog("进度变化："+progress+","+fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                DLog.eLog("开始拖拽滑块："+seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                DLog.eLog("停止拖拽滑块："+seekBar.getProgress());
            }
        });
    }

    @OnClick({R.id.bt_start,R.id.bt_start_seek})
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
            case R.id.bt_start_seek:
                mTimer = new Timer();
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count+=100;
                                if (count>10000) {
                                    count = 10000;
                                }
                                seekBar.setProgress(count);
                                if (count == 10000) {
                                    mTimer.cancel();
                                    mTimerTask.cancel();
                                }
                            }
                        });
                    }
                };
                mTimer.schedule(mTimerTask, 0, 100);
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
