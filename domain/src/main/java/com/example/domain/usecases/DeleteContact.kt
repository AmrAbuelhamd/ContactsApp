package com.example.domain.usecases

interface DeleteContact {
    suspend operator fun invoke(contactId: Int)
}