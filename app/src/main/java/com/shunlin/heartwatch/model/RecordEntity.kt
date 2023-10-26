package com.shunlin.heartwatch.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Record")
@Parcelize
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var sys: Int = 0,
    var dias: Int = 0,
    var time: Long = System.currentTimeMillis(),
    var level: Int = 0,
    var type: Int = RecordItemType.record
) : Parcelable


object RecordItemType{
    const val title:Int = 0
    const val record:Int = 1
}