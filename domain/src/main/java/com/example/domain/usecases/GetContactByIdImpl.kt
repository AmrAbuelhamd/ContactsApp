package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class GetContactByIdImpl(private val contactsDataSource: ContactsDataSource) : GetContactById {
    override operator fun invoke(contactId: Int) =
        contactsDataSource.getContactById(contactId)
}