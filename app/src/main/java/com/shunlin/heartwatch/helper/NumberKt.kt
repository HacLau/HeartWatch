package com.shunlin.heartwatch.helper

import android.content.Context

fun Float.dp2px(context: Context):Float{
    return context.resources.displayMetrics.density * this
}

fun Int.dp2px(context: Context):Int{
    return (context.resources.displayMetrics.density * this).toInt()
}
fun Int.toTime():String{
    return if(this < 10){
        "0${this}"
    }else{
        "$this"
    }
}