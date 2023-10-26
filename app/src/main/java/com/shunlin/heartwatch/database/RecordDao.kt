package com.shunlin.heartwatch.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shunlin.heartwatch.model.RecordEntity

@Dao
abstract class RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(recordEntity: RecordEntity)

    @Delete
    abstract fun delete(recordEntity: RecordEntity)

    @Update
    abstract fun update(recordEntity: RecordEntity)

    @Query("select * from Record order by time desc")
    abstract fun queryAll(): MutableList<RecordEntity>
}