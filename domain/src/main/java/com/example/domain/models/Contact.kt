package com.example.domain.models

data class Contact(
    val id: Int,
    val name: String,
    val imgLocalPath: String,
    val phoneNumbers: List<Phone>,
    val email: String,
    val note: String,
    val ringtoneId: Int,
    val isFavorite: Boolean
)
