package com.example.data.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ContactEntity(
    @Embedded
    val user: UserEntity,
    @Relation(parentColumn = "userId", entityColumn = "phoneOwnerId")
    val phones: List<PhoneEntity>
)