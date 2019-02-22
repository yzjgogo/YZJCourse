package com.yin.yzjcourse.mykt.AndroidExtensions

import android.os.Bundle
import android.widget.Toast
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.AndroidExtensions.E3UseInDiyView.DiyActivity
import com.yin.yzjcourse.mykt.AndroidExtensions.E1UseInActivity.WeightActivity
import com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder.RecyActivity
import com.yin.yzjcourse.mykt.AndroidExtensions.E5MakeYourselfCache.CacheActivity
import kotlinx.android.synthetic.main.activity_extension.*
import org.jetbrains.anko.startActivity

/**
 * kotlin-android-extensions的更详细用法，请参考：
 * https://www.cnblogs.com/figozhg/p/7400435.html
 *
 * kotlin-android-extensions用处如下：
 * Activity和Fragment,参考[com.yin.yzjcourse.mykt.AndroidExtensions.E1UseInActivity],Fragment用法和Activity一样；
 * ViewHolder,参考[com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder]
 * 自定义View,参考[com.yin.yzjcourse.mykt.AndroidExtensions.E3UseInDiyView]
 * 类实现Parcelable,参考[com.yin.yzjcourse.mykt.AndroidExtensions.E4UseInGenerParcelable]
 * 其中ViewHolder和Parcelable使用时，需注意开启相应的功能才可以，因为该功能还处于实验阶段。
 * 具体开启方法参考app的gradle文件:[org.gradle.api.Project#androidExtensions]
 *
 * kotlin-android-extensions的原理：
 * 真的没有调用findViewById()吗？，其实kotlin-android-extensions内部也是调用了的，只是给我们做了很好的封装，我们无需自己调用，
 * 那么问题来了，如果我每次通过控件id访问控件，kotlin-android-extensions是不是都会重复调用依次findViewById()呢？
 * 当然不是，如果重复调用了，肯定会影响性能，kotlin-android-extensions内部采用了缓存策略，避免了findViewById()的重复调用。
 * 具体参考:[com.yin.yzjcourse.mykt.AndroidExtensions.E5MakeYourselfCache]
 *
 * 查看kotlin-android-extensions是如何调用findViewById()可通过如下方法查看，这也是查看kotlin代码的java版本的途径
 * Android Studio -> Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile
 */
class ExtensionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension)
        initView()
    }

    private fun initView() {
        btFind.setOnClickListener {
            startActivity<WeightActivity>()
        }
        btRecycle.setOnClickListener {
            startActivity<RecyActivity>()
        }
        btDiy.setOnClickListener {
            startActivity<DiyActivity>()
        }
        btParcel.setOnClickListener {
            Toast.makeText(this,"这个看一下ParcelBean类就可以了",Toast.LENGTH_SHORT).show()
        }
        btCache.setOnClickListener {
            startActivity<CacheActivity>()
        }
    }
}
