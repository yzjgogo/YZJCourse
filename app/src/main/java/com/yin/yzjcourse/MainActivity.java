package com.yin.yzjcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.ForeService.ForeServiceActivity;
import com.yin.yzjcourse.RxJava2.RxRelease2Activity;

//第一次从家里的电脑提交代码
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_fore_service).setOnClickListener(this);
        findViewById(R.id.bt_dialog_fragment).setOnClickListener(this);
        findViewById(R.id.bt_rxjava).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_fore_service:
                Integer i = null;
                i.toString();
                Intent intent = new Intent(this, ForeServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_dialog_fragment:
                CourseDialog dialog = CourseDialog.getInstance();
                dialog.show(getSupportFragmentManager(), "CourseDialog");
                break;
            case R.id.bt_rxjava:
                Intent intent1 = new Intent(this, RxRelease2Activity.class);
                startActivity(intent1);
                break;
        }
    }
}
