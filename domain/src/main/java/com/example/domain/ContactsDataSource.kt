package com.example.domain

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource {
    fun getContacts(keyWord: String): Flow<List<Contact>>
    suspend fun addNewContact(contact: Contact)
    suspend fun setAsFavorite(contactId: Int)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contactId: Int)
}   