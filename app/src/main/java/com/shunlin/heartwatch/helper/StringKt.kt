package com.shunlin.heartwatch.helper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.shunlin.heartwatch.BuildConfig

const val TAG = "StringKt"
fun String.logE() {
    if (BuildConfig.DEBUG){
        Log.e(TAG, this)
    }
}
fun String.logI() {
    if (BuildConfig.DEBUG){
        Log.i(TAG, this)
    }
}
fun String.logD() {
    if (BuildConfig.DEBUG){
        Log.d(TAG, this)
    }
}
fun String.logW() {
    if (BuildConfig.DEBUG){
        Log.w(TAG, this)
    }
}
fun String.logV() {
    if (BuildConfig.DEBUG){
        Log.v(TAG, this)
    }
}

fun String.toast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}