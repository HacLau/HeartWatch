package com.shunlin.heartwatch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.helper.DateKt
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.helper.other
import com.shunlin.heartwatch.helper.toTime
import com.shunlin.heartwatch.helper.toast
import com.shunlin.heartwatch.helper.yes

class DateAndTimePopupWindow(
    private val context: Context,
    private val currTime: Long,
    private val clickCancel: () -> Unit = {},
    private val clickSure: (Long) -> Unit
) : PopupWindow(context) {
    private var mCancel: Button
    private var mConfirm: Button

    private var mYear: HorizontalPicker
    private var mMonth: HorizontalPicker
    private var mDay: HorizontalPicker
    private var mHour: HorizontalPicker
    private var mSecond: HorizontalPicker

    private var mYearText: String = DateKt.getYear(currTime).toString()
    private var mMonthText: String = DateKt.getMonth(currTime).toString()
    private var mDayText: String = DateKt.getDay(currTime).toString()
    private var mHourText: String = DateKt.getHour(currTime).toString()
    private var mSecondText: String = DateKt.getMinute(currTime).toString()

    private lateinit var mYearList: MutableList<String>
    private lateinit var mMonthList: MutableList<String>
    private lateinit var mDayList: MutableList<String>
    private lateinit var mHourList: MutableList<String>
    private lateinit var mSecondList: MutableList<String>

    init {
        height = ViewGroup.LayoutParams.MATCH_PARENT
        width = ViewGroup.LayoutParams.MATCH_PARENT
        isFocusable = true
        background.alpha = 160
        animationStyle = R.style.popwin_anim_style
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_date, null, false)
        mCancel = contentView.findViewById(R.id.dialog_cancel)
        mConfirm = contentView.findViewById(R.id.dialog_confirm)
        mYear = contentView.findViewById(R.id.hp_year)
        mMonth = contentView.findViewById(R.id.hp_month)
        mDay = contentView.findViewById(R.id.hp_day)
        mHour = contentView.findViewById(R.id.hp_hour)
        mSecond = contentView.findViewById(R.id.hp_second)

        mCancel.setOnClickListener {
            dismiss()
            clickCancel.invoke()
        }

        mConfirm.setOnClickListener {
            DateKt.getMills(mYearText.toInt(), mMonthText.toInt(), mDayText .toInt(), mHourText.toInt(), mSecondText.toInt()).let {
                "${it}  ${System.currentTimeMillis()}  ${DateKt.setShowTime(it)}  ${DateKt.setShowTime(System.currentTimeMillis())}".logE()
                if (it > System.currentTimeMillis()) {
                    "The time is incorrect,it cannot exceed te current time".toast(context)
                } else {
                    dismiss()

                    clickSure.invoke(it)
                }
            }

        }
        setData()
    }


    private fun setData() {
        mYearList = getMutList(mYearText.toInt() - 1, mYearText.toInt() + 1)
        mMonthList = getMutList(1, 12)
        mHourList = getMutList(0, 23)
        mSecondList = getMutList(0, 59)

        mYear.setData(mYearList, mYearList.indexOf(mYearText))
        mMonth.setData(mMonthList, mMonthList.indexOf((mMonthText.toInt() + 1).toTime()))
        mHour.setData(mHourList, mHourList.indexOf(mHourText.toInt().toTime()))
        mSecond.setData(mSecondList, mSecondList.indexOf(mSecondText.toInt().toTime()))
        setDay()
        mYear.onSelect = { value, _ ->
            mYearText = value
            "year = ${mYearText}".logE()
            setDay()
        }
        mMonth.onSelect = { value, _ ->
            // add 1 in and -1 out
            mMonthText = (value.toInt() -1).toString()
            "month = ${mMonthText}".logE()
            setDay()
        }
        mDay.onSelect = { value, _ ->
            mDayText = value
            "day = ${mDayText}".logE()
        }
        mHour.onSelect = { value, _ ->
            mHourText = value
            "hour = ${mHourText}".logE()
        }
        mSecond.onSelect = { value, _ ->
            mSecondText = value
            "second = ${mSecondText}".logE()
        }
    }

    private fun setDay() {
        mDayList = getMutList(1, DateKt.getDay(mYearText.toInt(), mMonthText.toInt() + 1))
        mDay.setData(mDayList, (mDayText.toInt() > mDayList.size).yes { mDayList.size - 1 }.other { mDayList.indexOf(mDayText.toInt().toTime()) })
    }

    private fun getMutList(startNumber: Int, endNumber: Int): MutableList<String> {
        return mutableListOf<String>().apply {
            (startNumber..endNumber).forEach {
                add(it.toTime())
            }
        }
    }

}