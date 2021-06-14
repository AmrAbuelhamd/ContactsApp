package com.example.domain

import com.example.domain.models.Contact
import com.example.domain.models.SimpleContact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource {
    fun getContacts(keyWord: String?): Flow<List<SimpleContact>>
    suspend fun setAsFavorite(contactId: Int, isFavorite: Boolean)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contactId: Int)
    fun getContactById(contactId: Int): Flow<Contact>
}