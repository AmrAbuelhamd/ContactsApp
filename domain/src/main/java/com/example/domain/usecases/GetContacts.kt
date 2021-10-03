package com.example.domain.usecases

import com.example.domain.models.SimpleContact
import kotlinx.coroutines.flow.Flow

interface GetContacts {
    operator fun invoke(keyWord: String?): Flow<List<SimpleContact>>
}