package com.example.data.converters

import com.example.data.db.models.ContactEntity
import com.example.domain.models.Contact
import com.example.domain.models.Phone

fun ContactEntity.toDomain() = Contact(
    user.userId,
    user.name,
    user.imgLocalPath,
    phones.map { Phone(it.phoneId, it.phone, it.type) },
    user.email,
    user.note,
    user.ringtoneId,
    user.isFavorite
)