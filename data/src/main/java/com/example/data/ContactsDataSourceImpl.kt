package com.example.data

import com.example.data.converters.toDomain
import com.example.data.converters.toLocalEntity
import com.example.data.db.dao.PhoneDao
import com.example.data.db.dao.UserDao
import com.example.data.utils.toIntValue
import com.example.domain.ContactsDataSource
import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactsDataSourceImpl(private val userDao: UserDao, private val phoneDao: PhoneDao) :
    ContactsDataSource {
    override fun getContacts(keyWord: String): Flow<List<Contact>> {
        return if (keyWord.isBlank())
            userDao.getAllContacts()
                .map { list -> list.map { it.toDomain() } }
        else
            userDao.getAllContacts("%$keyWord%")
                .map { list -> list.map { it.toDomain() } }
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
}