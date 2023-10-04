package com.example.task3_network_db.data.local

import android.content.Context
import androidx.room.Room

object DatabaseClient {

    fun createDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context, UserDatabase::class.java, "UsersDB"
        ).build()
    }
}