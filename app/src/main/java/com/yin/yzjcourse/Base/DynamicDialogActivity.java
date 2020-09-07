package com.yin.yzjcourse.Base;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * 看AndroidManifest.xml中XMLDialogActivity的声明：android:theme="@style/DialogWebActivityTheme"
 * 通过java代码setTheme(@StyleRes final int resId)动态设置Activity为Dialog，有一个问题是，以setTheme()的方式设置时，android:windowIsTranslucent属性不生效，导致dialog四周的遮罩不是半透明黑色，而是
 * 不透明的黑色。但是也有办法解决这个问题，具体办法是：在Manifest中给整个activity设置一个样式android:theme="@style/DialogBaseTheme"，这个样式里包含<item name="android:windowIsTranslucent">true</item>
 * 这样再通过setTheme()设置时，就采用Manifest里指定的android:windowIsTranslucent了，setTheme()不生效也无所谓了。但是有一点建议是Manifest里设置的theme，这里是DialogBaseTheme，其parent的theme建议是activity原本activity样式
 * 的theme，例如是application中统一指定的android:theme。
 */
public class DynamicDialogActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DialogWebActivityTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_dialog);
    }
}
