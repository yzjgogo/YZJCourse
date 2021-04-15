package com.yin.yzjcourse.Jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;

/**
 * ViewModel的名字可以这样理解：
 * 它是介于view(视图，布局)和Model(数据)之间的一个东西，它起到了桥梁的作用，使视图和数据即能够分离开，也能够保持通信
 * ViewModel的生命周期是从activity开始创建，到activity最终销毁，但是不受activity旋转重建的影响，及时activity因为旋转而销毁重建，ViewModel仍然不变，仍然是同一个对象，
 * 内部维护的数据也不变。
 * 只有在activity最终销毁时，系统才会自动调用ViewModel的onCleared()方法；activity因旋转而销毁不会调用onCleared()
 *
 * 因为activity旋转时会发生销毁，所以ViewModel千万不要持有activity的实例或context，否则容易造成内存泄漏；
 * 如果想让ViewModel持有Context,则可以使用AndroidViewModel,它继承自ViewModel,且接收Application作为Context
 */
public class ViewModelActivity extends BaseActivity {
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        tvContent = findViewById(R.id.tv_content);
        TimerViewModel timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        timerViewModel.onTimeChangedListener = new TimerViewModel.OnTimeChangeListener() {
            @Override
            public void onTimeChanged(int second) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText(String.valueOf(second));
                    }
                });
            }
        };
        timerViewModel.startTiming();
    }
}