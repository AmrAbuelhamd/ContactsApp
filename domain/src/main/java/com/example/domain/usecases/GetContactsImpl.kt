package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class GetContactsImpl(private val contactsDataSource: ContactsDataSource) : GetContacts {
    override operator fun invoke(keyWord: String) = contactsDataSource.getContacts(keyWord)
}