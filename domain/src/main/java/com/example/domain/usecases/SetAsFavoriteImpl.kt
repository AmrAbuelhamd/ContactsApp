package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class SetAsFavoriteImpl(private val contactsDataSource: ContactsDataSource) : SetAsFavorite {
    override suspend operator fun invoke(contactId: Int) =
        contactsDataSource.setAsFavorite(contactId)
}