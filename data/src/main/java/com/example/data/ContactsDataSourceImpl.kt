package com.example.data

import com.example.data.converters.toDomain
import com.example.data.converters.toLocalEntity
import com.example.data.db.dao.PhoneDao
import com.example.data.db.dao.UserDao
import com.example.data.utils.toIntValue
import com.example.domain.ContactsDataSource
import com.example.domain.models.Contact
import com.example.domain.models.SimpleContact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ContactsDataSourceImpl(private val userDao: UserDao, private val phoneDao: PhoneDao) :
    ContactsDataSource {
    override fun getContacts(keyWord: String?): Flow<List<SimpleContact>> {
        return if (keyWord.isNullOrBlank())
            userDao.getAllContacts()
                .map { list -> list.map { it.toDomain() } }
                .distinctUntilChanged()
        else
            userDao.getAllContacts("%$keyWord%")
                .map { list -> list.map { it.toDomain() } }
                .distinctUntilChanged()
    }

    override suspend fun setAsFavorite(contactId: Int, isFavorite: Boolean) {
        userDao.setAsFavorite(contactId, isFavorite.toIntValue())
    }

    override suspend fun updateContact(contact: Contact) {
        val contactEntity = contact.toLocalEntity()
        val userId = userDao.insert(contactEntity.user)
        val phones = contactEntity.phones.map { it.copy(phoneOwnerId = userId.toInt()) }
        phoneDao.insert(phones)
    }

    override suspend fun deleteContact(contactId: Int) {
        userDao.delete(contactId)
        phoneDao.delete(contactId)
    }

    override fun getContactByIdFlow(contactId: Int): Flow<Contact?> {
        return userDao.getContactByIdFlow(contactId).map { it?.toDomain() }
    }

    override suspend fun getContactById(contactId: Int): Contact {
        return userDao.getContactById(contactId).toDomain()
    }
}