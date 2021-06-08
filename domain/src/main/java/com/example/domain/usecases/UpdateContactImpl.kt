package com.example.domain.usecases

import com.example.domain.ContactsDataSource
import com.example.domain.models.Contact

class UpdateContactImpl(private val contactsDataSource: ContactsDataSource) : UpdateContact {
    override suspend operator fun invoke(contact: Contact) =
        contactsDataSource.updateContact(contact)
}