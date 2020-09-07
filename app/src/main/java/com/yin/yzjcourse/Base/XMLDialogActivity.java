package com.yin.yzjcourse.Base;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * 看AndroidManifest.xml中XMLDialogActivity的声明：android:theme="@style/DialogWebActivityTheme"
 */
public class XMLDialogActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_dialog);
    }
}
