package com.yin.yzjcourse.Jetpack.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yin.yzjcourse.utils.DLog

class UploadLogWorker:Worker {
    constructor(context: Context,params:WorkerParameters):super(context,params)

    override fun doWork(): Result {
        DLog.eLog("执行doWork()")
        return Result.success()
    }
}