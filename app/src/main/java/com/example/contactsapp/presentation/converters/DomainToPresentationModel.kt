package com.example.contactsapp.presentation.converters

import androidx.lifecycle.MutableLiveData
import com.example.contactsapp.presentation.models.ContactUiModel
import com.example.contactsapp.presentation.models.PhoneUiModel
import com.example.domain.models.Contact
import com.example.domain.models.Phone

fun Phone.toPresentationModel() =
    PhoneUiModel(
        id,
        MutableLiveData(phone),
        MutableLiveData(""),
        MutableLiveData(type),
        MutableLiveData("")
    )

fun Contact.toPresentationModel() = ContactUiModel(
    id,
    MutableLiveData(name),
    MutableLiveData(""),
    MutableLiveData(imgLocalPath),
    MutableLiveData(phoneNumbers.map { it.toPresentationModel() }),
    MutableLiveData(email),
    MutableLiveData(""),
    MutableLiveData(note),
    MutableLiveData(""),
    MutableLiveData(ringtone),
    MutableLiveData(""),
    isFavorite
)