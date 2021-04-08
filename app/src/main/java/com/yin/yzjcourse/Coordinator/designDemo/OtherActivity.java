package com.yin.yzjcourse.Coordinator.designDemo;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * Created by think on 2016/6/20.
 */
public class OtherActivity extends BaseActivity implements View.OnClickListener {
    private TextInputLayout textInputLayout;
    private AppCompatButton button;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        textInputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        button = (AppCompatButton) findViewById(R.id.button);
        button.setOnClickListener(this);
        findViewById(R.id.show_snakebar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            String inputContent = textInputLayout.getEditText().getText().toString();
            /**
             * 如果在setError之前设置ErrorEnabled为true则，默认在EditText下方预留出用于显示错误提示的控件；
             * 否则默认不预留，只有当显示错误提示时才会改变布局，显示错误提示
             * 要改变其颜色则用：
             * <style name="AppTheme" parent="Theme.AppCompat.LightReceiver.NoActionBar">
             <item name="colorAccent">#3498db</item>
             </style>
             */
            textInputLayout.setError(inputContent);
        }
        if(v.getId() == R.id.show_snakebar){
            Snackbar.make(v, "弹出Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Log.i("yin","点击了一下");
                        }
                    }).setActionTextColor(Color.parseColor("#EE82EE")).show();
        }
    }
}
