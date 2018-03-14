package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationCodeActivity extends BaseActivity {

    private ListView mListView;
    private ArrayAdapter mAdapter;

    private Button mAddListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation_code);

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData());
        mListView.setAdapter(mAdapter);

        mAddListBtn = (Button) findViewById(R.id.addlist);
        mAddListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addAll(getData());
            }
        });

        /**
         * 代码实现LayoutAnimationController核心部分
         */
        //代码设置通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        LayoutAnimationController controller = new LayoutAnimationController(animation);   //得到一个LayoutAnimationController对象；
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);   //设置控件显示的顺序；
        controller.setDelay(0.3f);  //设置控件显示间隔时间；
        mListView.setLayoutAnimation(controller);//为ListView设置LayoutAnimationController属性；
        mListView.startLayoutAnimation();
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }
}
