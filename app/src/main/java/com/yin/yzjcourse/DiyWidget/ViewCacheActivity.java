package com.yin.yzjcourse.DiyWidget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * https://www.bbsmax.com/A/o75NWrbedW/
 *
 * https://my.oschina.net/u/1177694/blog/630840?p=1
 *
 * 获取View(ViewGroup)的Bitmap缓存
 *
 * 另外对于ViewGroup:
 * ViewGroup在绘制子view时，而外提供了两个方法
 * //使viewgroup里所有的子view开启cache
 * setChildrenDrawingCacheEnabled(boolean enabled)
 * //在绘制子view时，若该子view开启了cache, 则使用它的cache进行绘制，从而节省绘制时间。
 * setChildrenDrawnWithCacheEnabled(boolean enabled)
 */
public class ViewCacheActivity extends BaseActivity {
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.ll_root)
    LinearLayout ll_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cache);
    }

    @OnClick({R.id.bt_start,R.id.bt_recycle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                CacheView cacheView = new CacheView(this);
                //要获取它的cache先要通过setDrawingCacheEnable方法把cache开启
                ll_content.setDrawingCacheEnabled(true);
                //调用getDrawingCache方法就可以获得view的cache图片了
                Bitmap bitmap = ll_content.getDrawingCache();
                if (bitmap != null) {
                    cacheView.setCacheBitmap(bitmap, 50, 50);
                    //因为cacheView并没有设置layoutParams，所以是match_parent,但是视觉上真正有效的区域是bitmap的区域，其他区域完全透明，因此这个bitmap的置位很重要，可看成视觉上的cacheView的大小
                    ll_root.addView(cacheView);
                }
                //不可以在这里调用下面这两个，否则刚才创建的cacheView显示不出来
//                ll_content.destroyDrawingCache();
//                ll_content.setDrawingCacheEnabled(false);
                break;
            case R.id.bt_recycle:
                //获取cache通常会占用一定的内存，所以通常不需要的时候有必要对其进行清理，通过destroyDrawingCache（）或setDrawingCacheEnabled(false)实现
                //这里模拟点击按钮回收资源，一般是在获取的Bitmap缓存不再使用时调用这两个方法
                ll_content.destroyDrawingCache();
//                ll_content.setDrawingCacheEnabled(false);
                break;
        }
    }
}
