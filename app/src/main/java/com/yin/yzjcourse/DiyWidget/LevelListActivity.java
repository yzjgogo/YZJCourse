package com.yin.yzjcourse.DiyWidget;

import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelListActivity extends AppCompatActivity {

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
        levelListDrawable = (LevelListDrawable) iv.getDrawable();
    }

    @OnClick(R.id.bt)
    public void onClick() {
        int currnetLevel = Integer.parseInt(etLevel.getText().toString().trim());
        levelListDrawable.setLevel(currnetLevel);
        if (levelListDrawable.getCurrent() instanceof TransitionDrawable){
            TransitionDrawable transitionDrawable = (TransitionDrawable) levelListDrawable.getCurrent();
            transitionDrawable.startTransition(3000);
        }

//        int level = levelListDrawable.getLevel();
//        DLog.eLog("当前level:"+level);
//        if (level<1 || level>11){
//            levelListDrawable.setLevel(2);
//        } else{
//            levelListDrawable.setLevel(level+2);
//            if (levelListDrawable.getCurrent() instanceof TransitionDrawable){
//                TransitionDrawable transitionDrawable = (TransitionDrawable) levelListDrawable.getCurrent();
//                transitionDrawable.startTransition(3000);
//            }
//        }
    }
}
