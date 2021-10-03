package com.example.contactsapp.presentation.converters

import com.example.contactsapp.presentation.models.ContactUiModel
import com.example.contactsapp.presentation.models.PhoneUiModel
import com.example.domain.models.Contact
import com.example.domain.models.Phone

fun PhoneUiModel.toDomain() =
    Phone(
        id,
        phone?.value ?: "0",
        type?.value ?: "0"
    )

fun ContactUiModel.toDomain() = Contact(
    id,
    name?.value ?: "0",
    imgLocalPath?.value ?: "0",
    phoneNumbers?.value?.map { it.toDomain() } ?: listOf(),
    email?.value ?: "0",
    note?.value ?: "0",
    ringtone?.value ?: "0",
    isfavorite
)