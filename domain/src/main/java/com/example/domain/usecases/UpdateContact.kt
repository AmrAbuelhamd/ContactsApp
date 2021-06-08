package com.example.domain.usecases

import com.example.domain.models.Contact

interface UpdateContact {
    suspend operator fun invoke(contact: Contact)
}