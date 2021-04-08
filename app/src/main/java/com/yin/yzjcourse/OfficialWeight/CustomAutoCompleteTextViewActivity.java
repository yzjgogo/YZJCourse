package com.yin.yzjcourse.OfficialWeight;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.Dog;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAutoCompleteTextViewActivity extends BaseActivity {

    @BindView(R.id.tv_auto)
    AutoCompleteTextView tvAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_auto_complete_text_view);
        ButterKnife.bind(this);

//        directShowData();
        addTextChangeListener();
    }

    private void directShowData() {
        List<Dog> dogs = RequestNet();
        DLog.eLog("网络返回的："+dogs.toString());
        AutoCompleteDogsAdapter adapter = new AutoCompleteDogsAdapter(CustomAutoCompleteTextViewActivity.this, dogs);
        tvAuto.setAdapter(adapter);
//        tvAuto.afte
    }

    private void addTextChangeListener() {
        tvAuto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DLog.eLog("文本变化："+s.toString());
                String content  = s.toString();
                if (!TextUtils.isEmpty(content.trim())) {
                    DLog.eLog("执行了");
                    directShowData();
                }else {
                    DLog.eLog("没执行了");
                    tvAuto.setAdapter(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private List<Dog> RequestNet() {
        List<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dogs.add(new Dog("名字" + i, "年龄" + i, "性别" + i));
        }
        return dogs;
    }
}
