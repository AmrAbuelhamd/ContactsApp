package com.example.domain

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource {
    fun getContacts(keyWord: String): Flow<List<Contact>>
    suspend fun setAsFavorite(contactId: Int, isFavorite: Boolean)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contactId: Int)
}   