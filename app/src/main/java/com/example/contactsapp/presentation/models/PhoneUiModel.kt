package com.example.contactsapp.presentation.models

import androidx.lifecycle.MutableLiveData

data class PhoneUiModel(
    val id: Int,
    val phone: MutableLiveData<String> = MutableLiveData(),
    val phoneError: MutableLiveData<String> = MutableLiveData(),
    val type: MutableLiveData<String> = MutableLiveData(),
    val typeError: MutableLiveData<String> = MutableLiveData(),
)