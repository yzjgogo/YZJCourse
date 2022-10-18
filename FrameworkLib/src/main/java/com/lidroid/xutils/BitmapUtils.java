/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lidroid.xutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lidroid.xutils.bitmap.BitmapCacheListener;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig.LoadDataListener;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.lidroid.xutils.bitmap.core.AsyncDrawable;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.task.TaskHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;

import zhl.common.utils.MemeryInfoUtil;

public class BitmapUtils implements TaskHandler {

    private final Object pauseTaskLock = new Object();
    private boolean pauseTask = false;
    private boolean cancelAllTask = false;
    private Context context;
    private BitmapGlobalConfig globalConfig;
    private BitmapDisplayConfig defaultDisplayConfig;

    // ///////////////////////////////////////////// create
    // ///////////////////////////////////////////////////
    public BitmapUtils(Context context) {
        this(context, null);
    }

    public BitmapUtils(Context context, String diskCachePath) {
        if (context == null) {
            throw new IllegalArgumentException("context may not be null");
        }
        this.context = context.getApplicationContext();
        globalConfig = BitmapGlobalConfig.getInstance(this.context, diskCachePath);
        defaultDisplayConfig = new BitmapDisplayConfig();
    }

    public BitmapUtils(Context context, String diskCachePath, int memoryCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemoryCacheSize(memoryCacheSize);
    }

    public BitmapUtils(Context context, String diskCachePath, int memoryCacheSize, int diskCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemoryCacheSize(memoryCacheSize);
        globalConfig.setDiskCacheSize(diskCacheSize);
    }

    public BitmapUtils(Context context, String diskCachePath, float memoryCachePercent) {
        this(context, diskCachePath);
        globalConfig.setMemCacheSizePercent(memoryCachePercent);
    }

    public BitmapUtils(Context context, String diskCachePath, float memoryCachePercent, int diskCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemCacheSizePercent(memoryCachePercent);
        globalConfig.setDiskCacheSize(diskCacheSize);
    }

    // ////////////////////////////////////// config
    // ////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    private static <T extends View> BitmapLoadTask<T> getBitmapTaskFromContainer(T container, BitmapLoadCallBack<T> callBack) {
        if (container != null) {
            final Drawable drawable = callBack.getDrawable(container);
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable<T> asyncDrawable = (AsyncDrawable<T>) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    private static <T extends View> boolean bitmapLoadTaskExist(T container, String uri, BitmapLoadCallBack<T> callBack) {
        final BitmapLoadTask<T> oldLoadTask = getBitmapTaskFromContainer(container, callBack);

        if (oldLoadTask != null) {
            final String oldUrl = oldLoadTask.uri;
            if (TextUtils.isEmpty(oldUrl) || !oldUrl.equals(uri)) {
                oldLoadTask.cancel(true);
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 图片去色,返回灰度图片
     *
     * @param bmpOriginal 传入的图片
     * @return 去色后的图片
     */
    public static Bitmap toGrayScale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * @param bmp
     * @param contrast [0-230] 0-50降低对比度，50-230增加对比度
     * @param brightness [0 - 100]
     * @return
     * @author yuxh
     * @createTime 2015年7月22日 上午11:49:23
     */
    public static Bitmap contrast(Bitmap bmp, float contrast, float brightness) {
        float scale = 1;
        if (contrast < 50) {
            scale = contrast / 50f;
        } else if (contrast > 50) {
            scale = (contrast - 50) / 50f * 2.5f + 1;
        }
        float lum = 256 * brightness / 100 * (1f - scale);
        ColorMatrix cm = new ColorMatrix();
        cm.set(new float[]{scale, 0, 0, 0, lum, 0, scale, 0, 0, lum, 0, 0, scale, 0, lum, 0, 0, 0, 1, 0});
        return createNewBitmap(bmp, cm);
    }

    /**
     * 调节明暗度
     *
     * @param bitmap
     * @param brightness[0 - 100]之间
     * @return
     * @author yuxh
     * @createTime 2015年7月22日 上午11:44:55
     */
    public static Bitmap brightness(Bitmap bitmap, float brightness) {
        float lum = (brightness - 50) * 2 * 255 * 0.01f;
        ColorMatrix cm = new ColorMatrix();
        cm.set(new float[]{1, 0, 0, 0, lum, 0, 1, 0, 0, lum, 0, 0, 1, 0, lum, 0, 0, 0, 1, 0});
        return createNewBitmap(bitmap, cm);
    }

    /**
     * 生成新的Bitmap
     *
     * @param bmp
     * @param cm
     * @return
     * @author yuxh
     * @createTime 2015年7月22日 上午11:43:58
     */
    private static Bitmap createNewBitmap(Bitmap bmp, ColorMatrix cm) {
        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);
        return ret;
    }

    /**
     * URI转成bitmap
     *
     * @param uri
     * @return
     * @author yuxh
     * @createTime 2015年7月22日 下午8:33:47
     */
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(uri.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public BitmapUtils configDefaultLoadingImage(Drawable drawable) {
        defaultDisplayConfig.setLoadingDrawable(drawable);
        return this;
    }

    private void configDensity() {
        long l = MemeryInfoUtil.getmem_UNUSED(context);
        // long t = MemeryInfoUtil.getmem_TOLAL();
        if (l < 50 * 1024 * 8) {// 如果可用空间小于，50M那就把图片密度缩小1.5倍
            defaultDisplayConfig.setBitmapDensity((int) (defaultDisplayConfig.getTargetDensity() * 1.5));
        } else {
            defaultDisplayConfig.setBitmapDensity(defaultDisplayConfig.getTargetDensity());
        }
        defaultDisplayConfig.setTargetDensity(context);
        System.out.println("剩余空间:" + (l / 1024 / 8) + "M");
    }

    // TODO:modify 2014-05-25 by ylf
    public BitmapUtils configDefaultLoadingImage(int resId) {
        if (resId <= 0) {
            defaultDisplayConfig.setLoadingDrawable(null);
        } else {
            defaultDisplayConfig.setLoadingDrawable(context.getResources().getDrawable(resId));
        }
        return this;
    }

    // TODO:modify 2014-05-25 by ylf 加入加解密
    public BitmapUtils configIsEncryImage(Boolean isEncry) {
        defaultDisplayConfig.setIsEncry(isEncry);
        return this;
    }

    public BitmapUtils configDefaultLoadingImage(Bitmap bitmap) {
        defaultDisplayConfig.setLoadingDrawable(new BitmapDrawable(context.getResources(), bitmap));
        return this;
    }

    public BitmapUtils configDefaultLoadFailedImage(Drawable drawable) {
        defaultDisplayConfig.setLoadFailedDrawable(drawable);
        return this;
    }

    // TODO:modify 2014-05-25 by ylf
    public BitmapUtils configDefaultLoadFailedImage(int resId) {
        if (resId <= 0) {
            defaultDisplayConfig.setLoadFailedDrawable(null);
        } else {
            defaultDisplayConfig.setLoadFailedDrawable(context.getResources().getDrawable(resId));
        }

        return this;
    }

    public BitmapUtils configDefaultLoadFailedImage(Bitmap bitmap) {
        defaultDisplayConfig.setLoadFailedDrawable(new BitmapDrawable(context.getResources(), bitmap));
        return this;
    }

    public BitmapUtils configDefaultBitmapMaxSize(int maxWidth, int maxHeight) {
        defaultDisplayConfig.setBitmapMaxSize(new BitmapSize(maxWidth, maxHeight));
        return this;
    }

    public BitmapUtils configDefaultBitmapMaxSize(BitmapSize maxSize) {
        defaultDisplayConfig.setBitmapMaxSize(maxSize);
        return this;
    }

    public BitmapUtils configDefaultImageLoadAnimation(Animation animation) {
        defaultDisplayConfig.setAnimation(animation);
        return this;
    }

    public BitmapUtils configDefaultAutoRotation(boolean autoRotation) {
        defaultDisplayConfig.setAutoRotation(autoRotation);
        return this;
    }

    // TODO:modify 2014-05-25 by ylf 添加回调接口
    public BitmapUtils configSetLoadDataListener(LoadDataListener loadDataListener) {
        defaultDisplayConfig.setLoadDataListener(loadDataListener);
        return this;
    }

    public BitmapUtils configDefaultShowOriginal(boolean showOriginal) {
        defaultDisplayConfig.setShowOriginal(showOriginal);
        return this;
    }

    public BitmapUtils configDefaultBitmapConfig(Bitmap.Config config) {
        defaultDisplayConfig.setBitmapConfig(config);
        return this;
    }

    public BitmapUtils configDefaultDisplayConfig(BitmapDisplayConfig displayConfig) {
        defaultDisplayConfig = displayConfig;
        return this;
    }

    public BitmapUtils configDownloader(Downloader downloader) {
        globalConfig.setDownloader(downloader);
        return this;
    }

    public BitmapUtils configDefaultCacheExpiry(long defaultExpiry) {
        globalConfig.setDefaultCacheExpiry(defaultExpiry);
        return this;
    }

    public BitmapUtils configDefaultConnectTimeout(int connectTimeout) {
        globalConfig.setDefaultConnectTimeout(connectTimeout);
        return this;
    }

    public BitmapUtils configDefaultReadTimeout(int readTimeout) {
        globalConfig.setDefaultReadTimeout(readTimeout);
        return this;
    }

    public BitmapUtils configThreadPoolSize(int threadPoolSize) {
        globalConfig.setThreadPoolSize(threadPoolSize);
        return this;
    }

    public BitmapUtils configMemoryCacheEnabled(boolean enabled) {
        globalConfig.setMemoryCacheEnabled(enabled);
        return this;
    }

    // //////////////////////// display ////////////////////////////////////

    public BitmapUtils configDiskCacheEnabled(boolean enabled) {
        globalConfig.setDiskCacheEnabled(enabled);
        return this;
    }

    public BitmapUtils configDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        globalConfig.setFileNameGenerator(fileNameGenerator);
        return this;
    }

    public BitmapUtils configBitmapCacheListener(BitmapCacheListener listener) {
        globalConfig.setBitmapCacheListener(listener);
        return this;
    }

    public BitmapUtils configGlobalConfig(BitmapGlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    // ///////////////////////////////////////////// cache
    // /////////////////////////////////////////////////////////////////

    // //////////////////////// down ylf
    // 2014-05-25////////////////////////////////////
    public <T extends View> void downImage(Context mContext, String uri, BitmapLoadCallBack<T> callBack) {
        display((T) new ImageView(mContext), uri, null, callBack);
    }

    public <T extends View> void downImage(Context mContext, String uri) {
        display((T) new ImageView(mContext), uri, null, null);
    }

    public <T extends View> void downImage(Context mContext, String uri, BitmapDisplayConfig displayConfig, BitmapLoadCallBack<T> callBack) {
        display((T) new ImageView(mContext), uri, displayConfig, callBack);
    }

    public <T extends View> void display(T container, String uri) {
        display(container, uri, null, null);
    }

    public <T extends View> void display(T container, String uri, BitmapDisplayConfig displayConfig) {
        display(container, uri, displayConfig, null);
    }

    public <T extends View> void display(T container, String uri, BitmapLoadCallBack<T> callBack) {
        display(container, uri, null, callBack);
    }

    public <T extends View> void display(T container, String uri, BitmapDisplayConfig displayConfig, BitmapLoadCallBack<T> callBack) {
        if (container == null) {
            return;
        }
        // 配置屏幕密度 modify by yuxh
        // configDensity();
        if (callBack == null) {
            callBack = new DefaultBitmapLoadCallBack<T>();
        } else {
            callBack.setIsCallBackByUser(true);
        }

        if (displayConfig == null || displayConfig == defaultDisplayConfig) {
            displayConfig = defaultDisplayConfig.cloneNew();
        }

        // Optimize Max Size
        BitmapSize size = displayConfig.getBitmapMaxSize();
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.optimizeMaxSizeByView(container, size.getWidth(), size.getHeight()));

        container.clearAnimation();

        if (TextUtils.isEmpty(uri)) {
            callBack.onLoadFailed(container, uri, displayConfig.getLoadFailedDrawable());
            return;
        }

        // start loading
        callBack.onPreLoad(container, uri, displayConfig);

        // find bitmap from mem cache.
        Bitmap bitmap = globalConfig.getBitmapCache().getBitmapFromMemCache(uri, displayConfig);

        if (bitmap != null) {
            callBack.onLoadStarted(container, uri, displayConfig);
            callBack.onLoadCompleted(container, uri, bitmap, displayConfig, BitmapLoadFrom.MEMORY_CACHE);
        } else if (!bitmapLoadTaskExist(container, uri, callBack)) {

            final BitmapLoadTask<T> loadTask = new BitmapLoadTask<T>(container, uri, displayConfig, callBack);

            // load bitmap from uri or diskCache
            PriorityExecutor executor = globalConfig.getBitmapLoadExecutor();
            File diskCacheFile = this.getBitmapFileFromDiskCache(uri);
            boolean diskCacheExist = diskCacheFile != null && diskCacheFile.exists();
            if (diskCacheExist && executor.isBusy()) {
                executor = globalConfig.getDiskCacheExecutor();
            }
            // set loading image
            Drawable loadingDrawable = displayConfig.getLoadingDrawable();

            callBack.setDrawable(container, new AsyncDrawable<T>(loadingDrawable, loadTask));

            loadTask.setPriority(displayConfig.getPriority());
            loadTask.executeOnExecutor(executor);
        }
    }

    public void clearCache() {
        globalConfig.clearCache();
    }

    public void clearMemoryCache() {
        globalConfig.clearMemoryCache();
    }

    public void clearDiskCache() {
        globalConfig.clearDiskCache();
    }

    // //////////////////////////////////////// tasks
    // //////////////////////////////////////////////////////////////////////

    public void clearCache(String uri) {
        globalConfig.clearCache(uri);
    }

    public void clearMemoryCache(String uri) {
        globalConfig.clearMemoryCache(uri);
    }

    public void clearDiskCache(String uri) {
        globalConfig.clearDiskCache(uri);
    }

    public void flushCache() {
        globalConfig.flushCache();
    }

    public void closeCache() {
        globalConfig.closeCache();
    }

    public File getBitmapFileFromDiskCache(String uri) {
        return globalConfig.getBitmapCache().getBitmapFileFromDiskCache(uri);
    }

    public Bitmap getBitmapFromMemCache(String uri, BitmapDisplayConfig config) {
        if (config == null) {
            config = defaultDisplayConfig;
        }
        return globalConfig.getBitmapCache().getBitmapFromMemCache(uri, config);
    }

    @Override
    public boolean supportPause() {
        return true;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean supportResume() {
        return true;
    }

    @Override
    public boolean supportCancel() {
        return true;
    }

    @Override
    public void pause() {
        pauseTask = true;
        flushCache();
    }

    @Override
    public void resume() {
        pauseTask = false;
        synchronized (pauseTaskLock) {
            pauseTaskLock.notifyAll();
        }
    }

    @Override
    public void cancel() {
        pauseTask = true;
        cancelAllTask = true;
        synchronized (pauseTaskLock) {
            pauseTaskLock.notifyAll();
        }
    }

    @Override
    public boolean isPaused() {
        return pauseTask;
    }

    @Override
    public boolean isCancelled() {
        return cancelAllTask;
    }

    public class BitmapLoadTask<T extends View> extends PriorityAsyncTask<Object, Object, Bitmap> {
        private static final int PROGRESS_LOAD_STARTED = 0;
        private static final int PROGRESS_LOADING = 1;
        private final String uri;
        private final WeakReference<T> containerReference;
        private final BitmapLoadCallBack<T> callBack;
        private final BitmapDisplayConfig displayConfig;
        private BitmapLoadFrom from = BitmapLoadFrom.DISK_CACHE;

        public BitmapLoadTask(T container, String uri, BitmapDisplayConfig config, BitmapLoadCallBack<T> callBack) {
            if (container == null || uri == null || config == null || callBack == null) {
                throw new IllegalArgumentException("args may not be null");
            }

            this.containerReference = new WeakReference<T>(container);
            this.callBack = callBack;
            this.uri = uri;
            this.displayConfig = config;
        }

        @Override
        protected Bitmap doInBackground(Object... params) {

            synchronized (pauseTaskLock) {
                while (pauseTask && !this.isCancelled()) {
                    try {
                        pauseTaskLock.wait();
                        if (cancelAllTask) {
                            return null;
                        }
                    } catch (Throwable e) {
                    }
                }
            }

            Bitmap bitmap = null;

            // get cache from disk cache
            if (!this.isCancelled() && (this.getTargetContainer() != null || callBack.getIsCallBackByUser())) {
                this.publishProgress(PROGRESS_LOAD_STARTED);
                bitmap = globalConfig.getBitmapCache().getBitmapFromDiskCache(uri, displayConfig);
            }

            // download image
            if (bitmap == null && !this.isCancelled() && (this.getTargetContainer() != null || callBack.getIsCallBackByUser())) {
                bitmap = globalConfig.getBitmapCache().downloadBitmap(uri, displayConfig, this);
                from = BitmapLoadFrom.URI;
            }

            return bitmap;
        }

        public void updateProgress(long total, long current) {
            this.publishProgress(PROGRESS_LOADING, total, current);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            if (values == null || values.length == 0)
                return;

            final T container = this.getTargetContainer();
            if (container == null && !callBack.getIsCallBackByUser())
                return;

            switch ((Integer) values[0]) {
                case PROGRESS_LOAD_STARTED:
                    callBack.onLoadStarted(container, uri, displayConfig);
                    break;
                case PROGRESS_LOADING:
                    if (values.length != 3)
                        return;
                    callBack.onLoading(container, uri, displayConfig, (Long) values[1], (Long) values[2]);
                    break;
                default:
                    break;
            }
        }

        // TODO:modify ylf 05-29 判断是否为用户传入回调，调用回调函数
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            final T container = this.getTargetContainer();
            if (container != null || callBack.getIsCallBackByUser()) {
                if (bitmap != null) {
                    callBack.onLoadCompleted(container, this.uri, bitmap, displayConfig, from);
                } else {
                    callBack.onLoadFailed(container, this.uri, displayConfig.getLoadFailedDrawable());
                }
            }
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            synchronized (pauseTaskLock) {
                pauseTaskLock.notifyAll();
            }
        }

        public T getTargetContainer() {
            final T container = containerReference.get();
            final BitmapLoadTask<T> bitmapWorkerTask = getBitmapTaskFromContainer(container, callBack);

            if (this == bitmapWorkerTask) {
                return container;
            }

            return null;
        }
    }
}
