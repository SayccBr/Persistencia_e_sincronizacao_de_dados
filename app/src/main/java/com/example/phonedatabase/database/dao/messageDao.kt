package com.example.phonedatabase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.phonedatabase.database.entities.Message

//interface que define os métodos para acessar o banco de dados
@Dao
interface MessageDao {
    @Insert
    suspend fun insert(message: Message)

    @Query("SELECT * FROM messages")
    suspend fun getAllMessages(): List<Message>

    @Query("DELETE FROM messages")
    suspend fun deleteAll()

}
