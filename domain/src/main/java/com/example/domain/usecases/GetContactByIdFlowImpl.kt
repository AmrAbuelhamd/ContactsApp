package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class GetContactByIdFlowImpl(private val contactsDataSource: ContactsDataSource) : GetContactByIdFlow {
    override operator fun invoke(contactId: Int) =
        contactsDataSource.getContactByIdFlow(contactId)
}