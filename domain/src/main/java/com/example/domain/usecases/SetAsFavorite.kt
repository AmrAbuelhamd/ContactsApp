package com.example.domain.usecases

interface SetAsFavorite {
    suspend operator fun invoke(contactId: Int, isFavorite: Boolean)
}