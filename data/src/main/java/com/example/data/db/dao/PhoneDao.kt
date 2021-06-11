package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.models.PhoneEntity

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(phoneEntity: List<PhoneEntity>)

    @Query("delete from phoneentity where phoneOwnerId like :contactId")
    fun delete(contactId: Int)
}