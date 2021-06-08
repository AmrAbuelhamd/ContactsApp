package com.example.domain.usecases

import com.example.domain.ContactsDataSource
import com.example.domain.models.Contact

class AddNewContactImpl(private val contactsDataSource: ContactsDataSource) : AddNewContact {
    override suspend operator fun invoke(contact: Contact) =
        contactsDataSource.addNewContact(contact)
}