package com.dicoding.nusatalaapp.data.repository

import androidx.compose.runtime.Composable
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.remote.dto.toModel
import com.dicoding.nusatalaapp.domain.model.Tutorial
import com.dicoding.nusatalaapp.domain.repository.TutorialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class TutorialRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TutorialRepository {
    override suspend fun getTutorialByLabel(token: String, labelId: Int): Flow<Result<Tutorial>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getTutorialByLabel(token, labelId).toModel()
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }
}