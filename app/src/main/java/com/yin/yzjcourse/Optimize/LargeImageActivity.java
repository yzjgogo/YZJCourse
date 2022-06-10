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
