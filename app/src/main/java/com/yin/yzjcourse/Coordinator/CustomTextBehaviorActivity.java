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
    private Button btGetTransY, btSetTransY, btGetTop, btGetY,btSetY,btSetTop;
    private TextView tvShowTransY, tvFab, tvShowTop, tvShowY;
    private EditText etInputTransY,etInputTop,etInputY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_behavior);
        btGetTransY = (Button) findViewById(R.id.bt_get_trans_y);
        btGetTop = (Button) findViewById(R.id.bt_get_top);
        btSetTransY = (Button) findViewById(R.id.bt_set_trans_y);
        tvShowTransY = (TextView) findViewById(R.id.tv_show_trans_y);
        tvShowTop = (TextView) findViewById(R.id.tv_show_top);
        tvFab = (TextView) findViewById(R.id.fab);
        etInputTransY = (EditText) findViewById(R.id.et_input_trans_y);
        btGetY = (Button) findViewById(R.id.bt_get_y);
        tvShowY = (TextView) findViewById(R.id.tv_show_y);
        btSetY = (Button) findViewById(R.id.bt_set_y);
        btSetTop = (Button) findViewById(R.id.bt_set_top);
        etInputY = (EditText) findViewById(R.id.et_input_y);
        etInputTop = (EditText) findViewById(R.id.et_input_top);
        //设置垂直偏移：负数向上偏移，正数向下偏移
        btSetTransY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次调用该方法都是在view的top值(无论怎么偏移都不变，getY()会随着偏移改变)的基础上进行垂直偏移，而不是在上次偏移后的getY()值的基础上偏移，且如果和上次的偏移值一样也不会偏移，因为不变
                //例如：view的top值是500,getY()也是500，第一次调用setTranslationY(100)则top不变，getY()在500的基础上变为600，第二次调用setTranslationY(200)则top还不变，getY()在500的基础上变为700
                //而不是在600的基础上变为800。
                //如果第一次调用setTranslationY(100)第二次也调用setTranslationY(100)，则第二次不执行，因为偏移距离相同，进去看该方法
                tvFab.setTranslationY(Float.parseFloat(etInputTransY.getText().toString().trim()));
            }
        });
        //获取垂直偏移
        btGetTransY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowTransY.setText(tvFab.getTranslationY() + "");
            }
        });
        //获取top坐标,getTop()的值始终是不变的，当没有发生偏移时getTop()=getY();getY()只是视觉上的位置，getTop()才是真正的相对父控件的位置
        btGetTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowTop.setText(tvFab.getTop() + "");
            }
        });
        //getY() = getTop()+getTranslationY();getY()只是视觉上的位置，getTop()才是真正的相对父控件的位置
        btGetY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowY.setText(tvFab.getY() + "");
            }
        });
        //setY()其实是偏移操作：setTranslationY(y - mTop)，比top值小说明是上移，比top值大说明是下移，偏移操作top始终不变
        btSetY.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tvFab.setY(Float.parseFloat(etInputY.getText().toString().trim()));
            }
        });
        //貌似没啥意义，set后(view大小发生变化)再get会回去到你set的值(view复原)，要是再get一次又会获取到原来的top值
        btSetTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tvFab.setTop(Integer.parseInt(etInputTop.getText().toString().trim()));
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
