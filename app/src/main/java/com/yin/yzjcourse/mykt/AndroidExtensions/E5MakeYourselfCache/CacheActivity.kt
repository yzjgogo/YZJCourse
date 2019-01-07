package com.yin.yzjcourse.mykt.AndroidExtensions.E5MakeYourselfCache

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions

/**
 * 可以进[CacheImplementation]的源码看看，kotlin对View的控件定义了三种缓存方式：
 * SPARSE_ARRAY  --> 更多时候会更高效
 * HASH_MAP --> 默认的缓存方式
 * NO_CACHE --> 不使用缓存
 *
 * 我们可以通过ContainerOptions注解，更改缓存方式。
 */
@ContainerOptions(CacheImplementation.SPARSE_ARRAY)
class CacheActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
    }
}
