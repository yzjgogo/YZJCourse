package com.yin.yzjcourse.Jetpack.work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.Jetpack.Room.Student
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import kotlinx.android.synthetic.main.activity_room.*
import java.util.concurrent.TimeUnit
const val WORK_TAG = "WORK_TAG"
class WorkActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_one_time,R.id.bt_work_info)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_one_time -> {
                doOneTimeWork()
            }
            R.id.bt_work_info -> {
                WorkManager.getInstance(this).getWorkInfosByTag(WORK_TAG);
            }
        }
    }

    private fun doOneTimeWork() {

        val constraints = Constraints.Builder().setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
                //设置触发条件
                .setConstraints(constraints)
                .setInitialDelay(1,TimeUnit.SECONDS)
                .setBackoffCriteria(BackoffPolicy.LINEAR,OneTimeWorkRequest.MIN_BACKOFF_MILLIS,TimeUnit.MILLISECONDS)
                .addTag(WORK_TAG)
                .build()

        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
    }
}