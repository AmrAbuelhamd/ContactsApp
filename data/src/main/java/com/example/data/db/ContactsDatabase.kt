package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.PhoneDao
import com.example.data.db.dao.UserDao
import com.example.data.db.models.PhoneEntity
import com.example.data.db.models.UserEntity

@Database(entities = [UserEntity::class, PhoneEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun phoneDao(): PhoneDao
}