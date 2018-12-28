package com.yin.yzjcourse.DesignModel.Decorate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

public class DecoratorPatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator_pattern);

        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        DLog.eLog("Circle with normal border");
        circle.draw();

        DLog.eLog("\nCircle of red border");
        redCircle.draw();

        DLog.eLog("\nRectangle of red border");
        redRectangle.draw();
    }
}
