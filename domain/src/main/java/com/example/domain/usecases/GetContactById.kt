package com.example.domain.usecases

import com.example.domain.models.Contact

interface GetContactById {
    suspend operator fun invoke(contactId: Int): Contact
}