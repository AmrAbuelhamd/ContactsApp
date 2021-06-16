package com.example.domain.usecases

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface GetContactById {
    suspend operator fun invoke(contactId: Int): Contact
}