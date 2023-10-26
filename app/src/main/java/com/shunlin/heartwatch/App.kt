package com.shunlin.heartwatch

import android.app.Application
import androidx.room.Room
import com.shunlin.heartwatch.database.RecordDao
import com.shunlin.heartwatch.database.RecordDatabase
import com.shunlin.heartwatch.helper.AppLifecycleHelper

lateinit var app: App

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
        registerActivityLifecycleCallbacks(AppLifecycleHelper())
    }
}