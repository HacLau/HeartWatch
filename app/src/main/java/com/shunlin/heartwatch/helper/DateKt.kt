package com.shunlin.heartwatch.helper

import java.util.Calendar

object DateKt {
    fun getYear(mills: Long = System.currentTimeMillis()): Int {
        val calender = Calendar.getInstance()
        calender.timeInMillis = mills
        return calender.get(Calendar.YEAR)
    }

    fun getMonth(mills: Long = System.currentTimeMillis()): Int {
        val calender = Calendar.getInstance()
        calender.timeInMillis = mills
        return calender.get(Calendar.MONTH)
    }

    fun getDay(mills: Long = System.currentTimeMillis()): Int {
        val calender = Calendar.getInstance()
        calender.timeInMillis = mills
        return calender.get(Calendar.DAY_OF_MONTH)
    }

    fun getHour(mills: Long = System.currentTimeMillis()): Int {
        val calender = Calendar.getInstance()
        calender.timeInMillis = mills
        return calender.get(Calendar.HOUR_OF_DAY)
    }

    fun getMinute(mills: Long = System.currentTimeMillis()): Int {
        val calender = Calendar.getInstance()
        calender.timeInMillis = mills
        return calender.get(Calendar.MINUTE)
    }

    fun getToday(): Long {
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() , getDay() + 1 , 0, 0, 0)
        return calender.timeInMillis
    }

    fun getYesterday(): Long {
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() , getDay() , 0, 0, 0)
        return calender.timeInMillis
    }

    fun getThisWeek(): Long {

        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() , getDay() + 1, 0, 0, 0)
        return calender.timeInMillis
    }

    fun getDayOfWeek(dayOfWeek: Int,weekOffset:Int,firstDayOfWeek: Int = Calendar.SUNDAY,):Long{
        val calender = Calendar.getInstance()
        if (dayOfWeek > Calendar.SATURDAY || dayOfWeek < Calendar.SUNDAY){
            return 0L
        }
        if (firstDayOfWeek > Calendar.SATURDAY || firstDayOfWeek < Calendar.SUNDAY){
            return 0L
        }
        calender.firstDayOfWeek = firstDayOfWeek
        calender.add(Calendar.WEEK_OF_MONTH,weekOffset)
        calender.set(Calendar.DAY_OF_WEEK,dayOfWeek)
        calender.set(Calendar.HOUR,0)
        calender.set(Calendar.MINUTE,0)
        calender.set(Calendar.SECOND,0)
        calender.set(Calendar.MILLISECOND,0)
        return calender.timeInMillis

    }

    fun getLastWeek():Long{
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() , getDay() - 7, 0, 0, 0)
        return calender.timeInMillis
    }

    fun getThisMonth(): Long {
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() + 1, 1, 0, 0, 0)
        return calender.timeInMillis
    }

    fun getLastMonth(): Long {
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth(), 1, 0, 0, 0)
        return calender.timeInMillis
    }

    fun getLastLastMonth(): Long {
        val calender = Calendar.getInstance()
        calender.set(getYear(), getMonth() - 1, 1, 0, 0, 0)
        return calender.timeInMillis
    }

    fun setShowTime(mills: Long): String {
        return "${getYear(mills)}.${(getMonth(mills) + 1).toTime()}.${getDay(mills).toTime()} ${getHour(mills).toTime()}:${getMinute(mills).toTime()}"
    }
    fun getDay(year:Int,month:Int):Int{
        val calender = Calendar.getInstance()
        calender.set(year,month,1)
        calender.add(Calendar.DATE,-1)
        return calender.get(Calendar.DATE)
    }

    fun getMills(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long {
        val calender = Calendar.getInstance()
        calender.set(Calendar.YEAR,year)
        calender.set(Calendar.MONTH,month)
        calender.set(Calendar.DAY_OF_MONTH,day)
        calender.set(Calendar.HOUR_OF_DAY,hour)
        calender.set(Calendar.MINUTE,minute)
        return calender.timeInMillis
    }

}