package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
/**
 https://blog.csdn.net/qq_21154101/article/details/105170954
 https://wenku.baidu.com/view/09685706de36a32d7375a417866fb84ae45cc3ae.html
 https://www.freesion.com/article/73441100030/
 https://blog.51cto.com/u_14202100/5189185
 hongyang的CSDN博客：https://blog.csdn.net/lmj623565791/article/details/49300989?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165484306616782248554182%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=165484306616782248554182&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~rank_v31_ecpm-1-49300989-null-null.nonecase&utm_term=BitmapRegionDecoder&spm=1018.2226.3001.4450
 * */
public class LargeImageActivity extends BaseActivity {
    private HighImageView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
        photoView = findViewById(R.id.high_img);
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("111.jpg");
            BitmapFactory.Options options = new BitmapFactory.Options();
            //为了只拿图片的宽高
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            photoView.setImage(inputStream, bitmap.getWidth(), bitmap.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
