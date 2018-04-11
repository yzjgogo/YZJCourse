package com.yin.yzjcourse.Optimize;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RemoveListenerActivity extends BaseActivity {

    @BindView(R.id.view)
    EditText view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_listener);
        ButterKnife.bind(this);
        addViewTreeListener();
        addListener();
    }

    private void addListener() {
//                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ALL);
//        sensorManager.registerListener((SensorEventListener) this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
//        //不需要用的时候记得移除监听
//        sensorManager.unregisterListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void addViewTreeListener() {
        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                view.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
            }
        });
    }
}
