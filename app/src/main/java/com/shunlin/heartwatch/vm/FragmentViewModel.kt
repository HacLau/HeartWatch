package com.shunlin.heartwatch.vm

import androidx.lifecycle.ViewModel
import com.shunlin.heartwatch.ui.RecordAdapter
import com.shunlin.heartwatch.helper.RecordType
import com.shunlin.heartwatch.ui.RecordPopupWindow

class FragmentViewModel : ViewModel() {

    lateinit var recordAdapter: RecordAdapter
    var recordType: String = RecordType.RECENT
    var recordPopupWindow: RecordPopupWindow? = null
}