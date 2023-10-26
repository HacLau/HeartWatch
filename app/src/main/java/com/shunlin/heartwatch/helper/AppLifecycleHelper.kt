package com.shunlin.heartwatch.helper

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.shunlin.heartwatch.app
import com.shunlin.heartwatch.view.ScreenActivity
import com.shunlin.heartwatch.view.StartActivity

class AppLifecycleHelper : Application.ActivityLifecycleCallbacks {
    private var runningActivities = 0
    private var inBackgroundTime = 0L
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (runningActivities == 0) {
            if (SharedHelper.launchedStart.not()) {
                startStartActivity()
            } else if (System.currentTimeMillis() - inBackgroundTime > 5000) {
                startScreenActivity()
            }
        }
        runningActivities++
    }

    private fun startScreenActivity() {
        app.startActivity(Intent(app, ScreenActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        runningActivities--
        inBackgroundTime = System.currentTimeMillis()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    private fun startStartActivity() {

        app.startActivity(Intent(app, StartActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        SharedHelper.launchedStart = true
    }
}