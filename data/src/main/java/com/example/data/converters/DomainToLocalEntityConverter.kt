package com.example.data.converters

import com.example.data.db.models.pojo.ContactEntity
import com.example.data.db.models.PhoneEntity
import com.example.data.db.models.UserEntity
import com.example.domain.models.Contact

fun Contact.toLocalEntity() = ContactEntity(
    UserEntity(id, name, imgLocalPath, email, note, ringtoneId, isFavorite),
    phoneNumbers.map { PhoneEntity(it.id, id, it.phone, it.type) }
)