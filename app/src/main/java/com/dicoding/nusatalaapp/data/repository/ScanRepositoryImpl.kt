package com.dicoding.nusatalaapp.data.repository

import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.domain.repository.ScanRepository
import java.io.File
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.data.remote.dto.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException

class ScanRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ScanRepository {
    override suspend fun scan(token: String, file: File): Flow<Result<Int>> = flow {
        try {
            val requestImgFile = file.asRequestBody("image/jpeg".toMediaType())
            val imgMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo", file.name, requestImgFile
            )
            emit(Result.Loading)
            val response = apiService.scan(token, imgMultiPart).toModel()
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        } catch (exception: IOException) {
            emit(Result.Error(exception.message.toString()))
        }
    }
}