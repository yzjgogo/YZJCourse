package com.yin.yzjcourse.Coordinator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class CustomTextBehaviorActivity extends BaseActivity {
    private Button btGetY, btSetY, btGet;
    private TextView tvShowY, tvFab, tvShow;
    private EditText etInputY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_behavior);
        btGetY = (Button) findViewById(R.id.bt_get_y);
        btGet = (Button) findViewById(R.id.bt_get);
        btSetY = (Button) findViewById(R.id.bt_set_y);
        tvShowY = (TextView) findViewById(R.id.tv_show_y);
        tvShow = (TextView) findViewById(R.id.tv_show);
        tvFab = (TextView) findViewById(R.id.fab);
        etInputY = (EditText) findViewById(R.id.et_input_y);
        //设置垂直偏移：负数向上偏移，正数向下偏移
        btSetY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次调用该方法都是在view原始top值的基础上进行垂直偏移，而不是在上次偏移后的top值的基础上偏移，且如果和上次的偏移值一样也不会偏移，因为不变
                //例如：view的原始top值是500，第一次调用setTranslationY(100)则top在500的基础上变为600，第二次调用setTranslationY(200)则top也是在500的基础上变为700
                //而不是在600的基础上变为800。
                //如果第一次调用setTranslationY(100)第二次也调用setTranslationY(100)，则第二次不执行，因为偏移距离相同，进去看该方法
                tvFab.setTranslationY(Float.parseFloat(etInputY.getText().toString().trim()));
            }
        });
        //获取垂直偏移
        btGetY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowY.setText(tvFab.getTranslationY() + "");
            }
        });
        //获取top坐标
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShow.setText(tvFab.getY() + "");
            }
        });
        //通过setTranslationY等方法使view发生偏移则是真正的view的移动，无论怎么偏移都会相应单击事件
        tvFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Toast.makeText(MainActivity.this,"点我了",Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "我什么时候都能点", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
