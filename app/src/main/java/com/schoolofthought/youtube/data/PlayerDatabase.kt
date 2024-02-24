package com.schoolofthought.youtube.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.schoolofthought.youtube.data.model.VideoLinks

@Database(entities = [VideoLinks::class], version = 1)
abstract class PlayerDatabase:RoomDatabase() {
    abstract fun userDao(): PlayerDao

//    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "database-name"
//    ).build()
}