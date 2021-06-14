package com.example.data.db.dao

import androidx.room.*
import com.example.data.db.models.UserEntity
import com.example.data.db.models.pojo.ContactEntity
import com.example.data.db.models.pojo.SimpleContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity): Long

    @Query("delete from userentity where userId like :contactId")
    suspend fun delete(contactId: Int)

    @Query("update userentity set isFavorite = :isFavorite where userId like :contactId")
    suspend fun setAsFavorite(contactId: Int, isFavorite: Int)

    @Transaction
    @Query("SELECT * FROM userentity where userId like :contactId")
    suspend fun getContactById(contactId: Int): ContactEntity

    @Transaction
    @Query("SELECT * FROM userentity where userId like :contactId")
    fun getContactByIdFlow(contactId: Int): Flow<ContactEntity>

    @Query("SELECT userId as id, name, imgLocalPath FROM userentity order by name")
    fun getAllContacts(): Flow<List<SimpleContactEntity>>

    @Query("SELECT userId as id, name, imgLocalPath FROM userentity where name like :keyWord order by name")
    fun getAllContacts(keyWord: String): Flow<List<SimpleContactEntity>>
}