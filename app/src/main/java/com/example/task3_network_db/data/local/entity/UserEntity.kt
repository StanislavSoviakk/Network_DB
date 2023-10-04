package com.example.task3_network_db.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.task3_network_db.domain.model.User

@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val title: String,
    val phone: String,
    val gender: String,
    val country: String,
    val email: String,
    val picture: String,
    @PrimaryKey val uuid: String
)

fun UserEntity.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        title = title,
        gender = gender,
        phone = phone,
        country = country,
        email = email,
        picture = picture,
        uuid = uuid
    )
}
