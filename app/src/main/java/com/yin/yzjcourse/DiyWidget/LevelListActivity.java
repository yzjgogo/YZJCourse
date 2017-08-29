package com.yin.yzjcourse.DiyWidget;

import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelListActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.et_level)
    EditText etLevel;
    private LevelListDrawable levelListDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);
        ButterKnife.bind(this);
        levelListDrawable = (LevelListDrawable) iv.getDrawable();//android:src="@drawable/level_list_drawable"
//        levelListDrawable = (LevelListDrawable) iv.getBackground();//android:background="@drawable/level_list_drawable"
    }

    /**
     * 给定LevelListDrawable一个level值(setLevel),就会从<level-list>标签下匹配每一个<item>标签，
     * 直到找到某一个item的minLevel<level<maxLevel，就取该item的drawable资源。如果没有匹配到则不显示drawable资源
     */
    @OnClick(R.id.bt)
    public void onClick() {
        int currnetLevel = Integer.parseInt(etLevel.getText().toString().trim());
        levelListDrawable.setLevel(currnetLevel);
        if (levelListDrawable.getCurrent() instanceof TransitionDrawable) {
            TransitionDrawable transitionDrawable = (TransitionDrawable) levelListDrawable.getCurrent();
            transitionDrawable.startTransition(3000);
        }
    }
}
