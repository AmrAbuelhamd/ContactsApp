package com.example.data

import com.example.domain.ContactsDataSource
import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

class ContactsDataSourceImpl: ContactsDataSource {
    override fun getContacts(keyWord: String): Flow<List<Contact>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewContact(contact: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun setAsFavorite(contactId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateContact(contact: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteContact(contactId: Int) {
        TODO("Not yet implemented")
    }
}