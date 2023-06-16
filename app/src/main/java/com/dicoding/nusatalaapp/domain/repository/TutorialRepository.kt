package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.Tutorial
import kotlinx.coroutines.flow.Flow
import com.dicoding.nusatalaapp.common.Result

interface TutorialRepository {
    suspend fun getTutorialByLabel(token: String, labelId: Int): Flow<Result<Tutorial>>
}