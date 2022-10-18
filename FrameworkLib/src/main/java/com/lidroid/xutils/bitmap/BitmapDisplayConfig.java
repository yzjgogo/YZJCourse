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

package com.lidroid.xutils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.animation.Animation;

import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.task.Priority;

import java.io.FileInputStream;
import java.io.OutputStream;

public class BitmapDisplayConfig {

    private BitmapSize bitmapMaxSize;
    private Animation animation;
    private Drawable loadingDrawable;
    private Drawable loadFailedDrawable;
    private boolean autoRotation = false;
    private boolean showOriginal = false;
    private Bitmap.Config bitmapConfig = Bitmap.Config.RGB_565;
    private BitmapFactory bitmapFactory;
    // TODO:modify 2014-05-25 ylf 图片是否加解密处理
    private boolean isEncry = false;

    private Priority priority;
    private int targetDensity;
    private int bitmapDensity;
    private LoadDataListener loadDataListener;

    public BitmapDisplayConfig() {
    }

    /**
     * @return
     * @author yuxh
     * @createTime 2015年8月19日 下午7:54:49
     */
    public int getBitmapDensity() {
        return bitmapDensity;
    }

    /**
     * @param bitmap
     * @author yuxh
     * @createTime 2015年8月19日 下午7:54:10
     */
    public void setBitmapDensity(int density) {
        this.bitmapDensity = density;
    }

    /**
     * @return
     * @author yuxh
     * @createTime 2015年8月19日 下午7:54:44
     */
    public int getTargetDensity() {
        return targetDensity;
    }

    /**
     * @param context
     * @author yuxh
     * @createTime 2015年8月19日 下午7:54:13
     */
    public void setTargetDensity(Context context) {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.targetDensity = d.densityDpi;
    }

    public BitmapSize getBitmapMaxSize() {
        return bitmapMaxSize == null ? BitmapSize.ZERO : bitmapMaxSize;
    }

    public void setBitmapMaxSize(BitmapSize bitmapMaxSize) {
        this.bitmapMaxSize = bitmapMaxSize;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Drawable getLoadingDrawable() {
        return loadingDrawable;
    }

    public void setLoadingDrawable(Drawable loadingDrawable) {
        this.loadingDrawable = loadingDrawable;
    }

    public Drawable getLoadFailedDrawable() {
        return loadFailedDrawable;
    }

    public void setLoadFailedDrawable(Drawable loadFailedDrawable) {
        this.loadFailedDrawable = loadFailedDrawable;
    }

    public boolean isAutoRotation() {
        return autoRotation;
    }

    public void setAutoRotation(boolean autoRotation) {
        this.autoRotation = autoRotation;
    }

    public boolean isShowOriginal() {
        return showOriginal;
    }

    public void setShowOriginal(boolean showOriginal) {
        this.showOriginal = showOriginal;
    }

    public Bitmap.Config getBitmapConfig() {
        return bitmapConfig;
    }

    public void setBitmapConfig(Bitmap.Config bitmapConfig) {
        this.bitmapConfig = bitmapConfig;
    }

    public BitmapFactory getBitmapFactory() {
        return bitmapFactory;
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        this.bitmapFactory = bitmapFactory;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean getIsEncry() {
        return isEncry;
    }

    public void setIsEncry(boolean isEncry) {
        this.isEncry = isEncry;
    }

    public LoadDataListener getLoadDataListener() {
        return loadDataListener;
    }

    public void setLoadDataListener(LoadDataListener loadDataListener) {
        this.loadDataListener = loadDataListener;
    }

    @Override
    public String toString() {
        return (isShowOriginal() ? "" : bitmapMaxSize.toString()) + (bitmapFactory == null ? "" : bitmapFactory.getClass().getName());
    }

    public BitmapDisplayConfig cloneNew() {
        BitmapDisplayConfig config = new BitmapDisplayConfig();
        config.bitmapMaxSize = this.bitmapMaxSize;
        config.animation = this.animation;
        config.loadingDrawable = this.loadingDrawable;
        config.loadFailedDrawable = this.loadFailedDrawable;
        config.autoRotation = this.autoRotation;
        config.showOriginal = this.showOriginal;
        config.bitmapConfig = this.bitmapConfig;
        config.bitmapFactory = this.bitmapFactory;
        config.priority = this.priority;
        config.isEncry = this.isEncry;
        config.loadDataListener = this.loadDataListener;
        config.targetDensity = this.targetDensity;
        config.bitmapDensity = this.bitmapDensity;
        return config;
    }

    public interface LoadDataListener {

        void BufferLoaded(FileInputStream ipsStream, OutputStream outputStream);
    }
}
