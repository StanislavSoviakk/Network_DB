package com.example.task3_network_db.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task3_network_db.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class], version = 1, exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao
}