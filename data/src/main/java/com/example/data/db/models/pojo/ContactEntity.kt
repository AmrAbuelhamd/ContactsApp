package com.example.data.db.models.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.db.models.PhoneEntity
import com.example.data.db.models.UserEntity

data class ContactEntity(
    @Embedded
    val user: UserEntity,
    @Relation(parentColumn = "userId", entityColumn = "phoneOwnerId")
    val phones: List<PhoneEntity>
)