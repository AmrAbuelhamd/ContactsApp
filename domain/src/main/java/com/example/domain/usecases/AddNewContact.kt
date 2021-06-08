package com.example.domain.usecases

import com.example.domain.models.Contact

interface AddNewContact {
    suspend operator fun invoke(contact: Contact)
}