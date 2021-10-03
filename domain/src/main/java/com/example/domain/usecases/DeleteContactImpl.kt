package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class DeleteContactImpl(private val contactsDataSource: ContactsDataSource) : DeleteContact {
    override suspend operator fun invoke(contactId: Int) =
        contactsDataSource.deleteContact(contactId)
}