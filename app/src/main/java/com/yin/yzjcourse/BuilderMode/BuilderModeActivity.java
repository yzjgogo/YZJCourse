package com.yin.yzjcourse.BuilderMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode.Cookies;
import com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode.Director;
import com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode.RoundCookiesBuilder;
import com.yin.yzjcourse.BuilderMode.ClassicalBuilderMode.SquareCookiesBuilder;
import com.yin.yzjcourse.BuilderMode.ModernBuilderMode.Person;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuilderModeActivity extends AppCompatActivity {

    @BindView(R.id.tv_test_result)
    TextView tvTestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder_mode);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_classical_builder, R.id.bt_modern_builder})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             *  经典的Builder模式有四个参与者

             Product：被构造的复杂对象
             Builder：抽象接口，用来定义创建Product对象的各个组成部
             件的操作。
             ConcreteBuilder：Builder接口的具体实现，可以定义多个，
             是实际构建Product对象的类，同时会提供一个返回Product的接
             口。
             Director：Builder接口的构造者和使用者。
             */
            case R.id.bt_classical_builder:
                //二者的区别就是对Director传入不同形状饼干的Builder的实现类。
                //而Director的对象调用的方法都是createCookies()和getCookies()
                //所以经典的Builder模式重点在于抽象出对象创建的步骤，并通过调用不同的具体实现类从而得到不同的结果
                Cookies squareCookie = new Director(new SquareCookiesBuilder()).createCookies().getCookies();
                Cookies roundCookie = new Director(new RoundCookiesBuilder()).createCookies().getCookies();
                tvTestResult.setText("经典模式创建了两个饼干："+squareCookie.toString()+"、"+roundCookie.toString());
                break;
            case R.id.bt_modern_builder:
                /**
                 1.Person类的构造函数是私有的。这样就不能直接实例化这个类
                 2.Person类是不可变的。里面的属性都是final的。只能在构造函数中初始化。然后提供了属性的get函数，可以去获取值。
                 3.连贯性，这个Person的创建是：
                 new Person.Builder().name("青蛙ing").gender("男").age("20").build();
                 就问你这么写爽不爽。!!
                 */
                Person person = new Person.Builder().age("20").gender("男").name("张三").build();
                tvTestResult.setText("变种模式创建了个人："+person.toString());
                break;
        }
    }
}
