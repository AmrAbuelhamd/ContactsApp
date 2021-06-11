package com.example.data.db.dao

import androidx.room.*
import com.example.data.db.models.ContactEntity
import com.example.data.db.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity): Long

    @Query("delete from userentity where userId like :contactId")
    fun delete(contactId: Int)

    @Query("update userentity set isFavorite = :isFavorite where userId like :contactId")
    fun setAsFavorite(contactId: Int, isFavorite: Int)

    @Transaction
    @Query("SELECT * FROM userentity")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Transaction
    @Query("SELECT * FROM userentity where name like :keyWord")
    fun getAllContacts(keyWord: String): Flow<List<ContactEntity>>
}