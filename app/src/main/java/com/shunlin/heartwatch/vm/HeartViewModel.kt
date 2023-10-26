package com.shunlin.heartwatch.vm

import androidx.lifecycle.ViewModel
import com.shunlin.heartwatch.ui.RecordAdapter
import com.shunlin.heartwatch.helper.RecordType
import com.shunlin.heartwatch.helper.logE
import java.util.Timer
import java.util.TimerTask


class HeartViewModel : ViewModel() {
    val recordType: String = RecordType.ALL
    lateinit var recordAdapter: RecordAdapter
    var progress = 0

    fun startTimeTask(onProgress:(Int)->Unit) {
        "startTimeTask".logE()
        Timer().schedule(object :TimerTask(){
            override fun run() {
                progress ++
                if (progress == 100){
                    cancel()
                }
                onProgress.invoke(progress)
            }

        },33,33)
    }


}