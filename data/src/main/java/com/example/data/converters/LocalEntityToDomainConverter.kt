package com.example.data.converters

import com.example.data.db.models.pojo.ContactEntity
import com.example.data.db.models.pojo.SimpleContactEntity
import com.example.domain.models.Contact
import com.example.domain.models.Phone
import com.example.domain.models.SimpleContact

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

fun SimpleContactEntity.toDomain() = SimpleContact(id, name, imgLocalPath)