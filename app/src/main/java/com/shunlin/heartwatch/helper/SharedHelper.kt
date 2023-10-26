package com.shunlin.heartwatch.helper

import android.annotation.SuppressLint
import com.shunlin.heartwatch.app


@SuppressLint("StaticFieldLeak")
object SharedHelper {
    var launchedStep by SharedUtil(app,"launchedStep",false)
    var launchedStart by SharedUtil(app,"launchedStart",false)
}