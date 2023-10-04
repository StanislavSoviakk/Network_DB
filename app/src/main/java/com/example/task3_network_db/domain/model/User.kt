package com.example.task3_network_db.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val title: String,
    val phone: String,
    val gender: String,
    val country: String,
    val email: String,
    val picture: String,
    val uuid: String
)