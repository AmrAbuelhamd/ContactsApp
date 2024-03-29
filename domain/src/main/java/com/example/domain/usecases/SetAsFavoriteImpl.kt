package com.example.domain.usecases

import com.example.domain.ContactsDataSource

class SetAsFavoriteImpl(private val contactsDataSource: ContactsDataSource) : SetAsFavorite {
    override suspend operator fun invoke(contactId: Int, isFavorite: Boolean) =
        contactsDataSource.setAsFavorite(contactId, isFavorite)
}