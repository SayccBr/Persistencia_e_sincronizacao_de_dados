package com.example.phonedatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phonedatabase.database.dao.MessageDao
import com.example.phonedatabase.database.entities.Message

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
