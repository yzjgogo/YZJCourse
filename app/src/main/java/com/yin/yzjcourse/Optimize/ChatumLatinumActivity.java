/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yin.yzjcourse.Optimize;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.yin.yzjcourse.R;

/**
    目前代码是孤独绘制的情况，解决办法都在注释里，可以通过手机开发者模式的查看过度绘制做前后对比
 */
public class ChatumLatinumActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatum_latinum);


        // The Theme's windowBackground is masked by the opaque background of the activity, and
        // the windowBackground causes an unnecessary overdraw. Nullifying the windowBackground
        // removes that overdraw.
        //我们在布局中设置了不透明的白色背景，遮挡住了主题默认的背景，如果不移除，就会引起过度绘制(重叠)
//        getWindow().setBackgroundDrawable(null);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_chatum_latinum_container, new ChatsFragment())
                    .commit();
        }
    }
}