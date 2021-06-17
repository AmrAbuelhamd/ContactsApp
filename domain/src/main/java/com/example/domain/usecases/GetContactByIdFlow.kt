package com.example.domain.usecases

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface GetContactByIdFlow {
    operator fun invoke(contactId: Int): Flow<Contact?>
}