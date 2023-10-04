package com.example.task3_network_db.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task3_network_db.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM userEntity")
    suspend fun clearDB()

    @Query("SELECT * FROM userEntity")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM userEntity WHERE uuid = :id")
    suspend fun getUserById(id: String): UserEntity
}