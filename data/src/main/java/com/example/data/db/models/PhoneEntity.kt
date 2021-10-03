package com.example.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true)
    val phoneId: Int,
    val phoneOwnerId:Int,
    val phone: String,
    val type: String
)