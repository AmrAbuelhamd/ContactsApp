package com.example.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String,
    val imgLocalPath: String,
    val email: String,
    val note: String,
    val ringtone: String,
    val isFavorite:Boolean
)