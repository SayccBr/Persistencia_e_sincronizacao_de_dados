package com.example.phonedatabase.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "message_database"
        ).build()
    }
}
