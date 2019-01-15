package com.yin.yzjcourse.DesignModel.P1策略模式;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DesignModel.P1策略模式.叫唤行为.具体的叫唤类s.Squeak;
import com.yin.yzjcourse.DesignModel.P1策略模式.飞行行为.具体的飞行类s.FlyNoWay;
import com.yin.yzjcourse.DesignModel.P1策略模式.鸭子基类.Duck;
import com.yin.yzjcourse.R;

public class StrategyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);


        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        mallard.setFlyBehavior(new FlyNoWay());
        mallard.setQuackBehavior(new Squeak());
    }
}
