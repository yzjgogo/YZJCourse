package com.yin.yzjcourse.OfficialWeight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 你也可以自定义adapter extends BaseAdapter implements Filterable
 * 必须实现Filterable接口，不然无法知道过滤规则，其实ArrayAdapter也实现了
 */
public class AutoCompleteActivity extends BaseActivity {

    @BindView(R.id.tv_auto)
    AutoCompleteTextView tvAuto;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ButterKnife.bind(this);
        String [] arr={"a","ab","abc","abcd","abcde","abcdef","abcdefg","abcdefgh"};
//        String [] arr={"a","ab","abc"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        tvAuto.setAdapter(arrayAdapter);
    }
}
