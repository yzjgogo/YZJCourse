package com.yin.yzjcourse.Optimize;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.IOException;
import java.io.InputStream;

/**
    0：

    1：对于drawable或mipmap里的图片：
    options.inSampleSize <=1;
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.vip_card_7_english,options)
    options.outWidth是原始图片物理像素的宽，即你电脑里右键看到的尺寸；
    bitmap.getWidth()可能与options.outWidth不一样，且放到不同的drawable或mipmap文件夹bitmap.getWidth()的值不一样；

    2：对于assets或sdcard里的图片：
    options.inSampleSize <=1;
    Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);
    options.outWidth == bitmap.getWidth()

    3：无论是assets、sdcard、drawable(mipmap)里的图片只要设置options.inSampleSize = 2;
       options.outWidth 和 bitmap.getWidth()的值都会变为1/2；

    4：BitmapFactory.Options不是全局的，new一个出来就是一个独立的实例
    BitmapFactory.Options options1 = new BitmapFactory.Options()
    BitmapFactory.Options options2 = new BitmapFactory.Options()
    options1与options2没关系，用到哪里就在哪里作用自己的属性，不覆盖不混淆；

     5：
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.vip_card_7_english,options)
    Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);
    .
    .
    如果options.inJustDecodeBounds == true,options能拿到图片的尺寸信息，即options.outWidth有值，但是bitmap为null；
    如果options.inJustDecodeBounds == false,options也能拿到图片的尺寸信息，即options.outWidth有值，并且bitmap不是null，bitmap.getWidth()也有值


 */
public class BitmapInSimpleSizeActivity extends BaseActivity {
    ImageView iv1,iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_size);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);

        //证明注释4：
//        BitmapFactory.Options options1 = new BitmapFactory.Options();
//        options1.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        options1.inJustDecodeBounds = true;
//        options1.inSampleSize = 4;
//        DLog.eLog("第一次1："+options1.inPreferredConfig+" , "+options1.inJustDecodeBounds+" , "+options1.inSampleSize);
//
//        BitmapFactory.Options options2 = new BitmapFactory.Options();
//        options2.inPreferredConfig = Bitmap.Config.RGB_565;
//        options2.inJustDecodeBounds = false;
//        options2.inSampleSize = 2;
//        DLog.eLog("第二次2："+options2.inPreferredConfig+" , "+options2.inJustDecodeBounds+" , "+options2.inSampleSize);


        //drawable里的图片
        Bitmap tempBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.vip_card_7_english);
        DLog.eLog("默认Bitmap的宽高1："+tempBitmap.getWidth()+" , "+tempBitmap.getHeight());

        BitmapFactory.Options tempOptions = new BitmapFactory.Options();
        tempOptions.inJustDecodeBounds = false;
        Bitmap tempBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.vip_card_7_english,tempOptions);
        DLog.eLog("再次Bitmap的宽高2："+tempBitmap2.getWidth()+" , "+tempBitmap2.getHeight()+" | "+tempOptions.outWidth+" , "+tempOptions.outHeight);
        iv1.setImageBitmap(tempBitmap2);

        BitmapFactory.Options tempOptions3 = new BitmapFactory.Options();
        tempOptions3.inJustDecodeBounds = false;
        tempOptions3.inSampleSize = 2;
        Bitmap tempBitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.vip_card_7_english,tempOptions3);
        DLog.eLog("再次Bitmap的宽高3："+tempBitmap3.getWidth()+" , "+tempBitmap3.getHeight()+" | "+tempOptions3.outWidth+" , "+tempOptions3.outHeight);
//        DLog.eLog("再次Bitmap的宽高："+tempOptions.outWidth+" , "+tempOptions.outHeight);
        iv2.setImageBitmap(tempBitmap3);

        DLog.eLog("-------------------------------------------------------------------------");

        //assets里的图片
        try {
            BitmapFactory.Options options3 = new BitmapFactory.Options();
            InputStream inputStream = getAssets().open("my_vip.webp");
            Bitmap sBitmap = BitmapFactory.decodeStream(inputStream,null,options3);
            DLog.eLog("assets图片1："+sBitmap.getWidth()+" , "+sBitmap.getHeight()+" | "+options3.outWidth+" , "+options3.outHeight);

            BitmapFactory.Options options4 = new BitmapFactory.Options();
            options4.inSampleSize = 2;
            InputStream inputStream4 = getAssets().open("my_vip.webp");
            Bitmap sBitmap4 = BitmapFactory.decodeStream(inputStream4,null,options4);
            DLog.eLog("assets图片2："+sBitmap4.getWidth()+" , "+sBitmap4.getHeight()+" | "+options4.outWidth+" , "+options4.outHeight);
        } catch (IOException e) {

        }


        //根据目标尺寸编译出bitmap
        Bitmap mBitmap = decodeSampledBitmapFromResource(getResources(),R.drawable.vip_card_7_english,270,156);
        DLog.eLog("获取结果的宽高："+mBitmap.getWidth()+" , "+mBitmap.getHeight());
//        iv1.setImageBitmap(mBitmap);
//        iv2.setImageBitmap(mBitmap);
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    /**
        注意：这个方法是根据options.outWidth来计算inSampleSize的，对于assets和sdcard里的图片，可以通用，对于drawable里的图片似乎要考虑
        是否应该根据Bitmap的宽高来计算simple，到时候自己按需求考虑下；

        根据目标尺寸reqWidth,reqHeight计算出目标inSampleSize

        采样率的理解参考图片：‘采样率inSampleSize.png’

     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        DLog.eLog("原来的宽高："+width+" , "+height);
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        DLog.eLog("采样值："+inSampleSize);
        return inSampleSize;
    }
}
