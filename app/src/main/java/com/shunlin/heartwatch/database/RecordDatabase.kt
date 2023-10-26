package com.shunlin.heartwatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shunlin.heartwatch.model.RecordEntity

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordDatabase :RoomDatabase(){
    abstract fun recordDao(): RecordDao

    companion object {
        private var INSTANCE: RecordDatabase? = null
        fun getDatabase(context: Context): RecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordDatabase::class.java,
                    "record_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}