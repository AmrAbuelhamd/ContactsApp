package com.example.contactsapp.presentation.models

import androidx.lifecycle.MutableLiveData

data class ContactUiModel(
    val id: Int,
    var name: MutableLiveData<String> = MutableLiveData(),
    var nameError: MutableLiveData<String> = MutableLiveData(),
    var imgLocalPath: MutableLiveData<String> = MutableLiveData(),
    var phoneNumbers: MutableLiveData<List<PhoneUiModel>> = MutableLiveData(
        listOf(
            PhoneUiModel(0),
            PhoneUiModel(0),
        )
    ),
    var email: MutableLiveData<String> = MutableLiveData(),
    var emailError: MutableLiveData<String> = MutableLiveData(),
    var note: MutableLiveData<String> = MutableLiveData(),
    var noteError: MutableLiveData<String> = MutableLiveData(),
    var ringtone: MutableLiveData<String> = MutableLiveData(),
    var ringtoneError: MutableLiveData<String> = MutableLiveData(),
    var isfavorite: Boolean = false
)
