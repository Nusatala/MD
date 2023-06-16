package com.dicoding.nusatalaapp.presentation.scan.result

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import com.dicoding.nusatalaapp.domain.use_case.scan.scan_image.ScanImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

@HiltViewModel
class ScanResultViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val scanImageUseCase: ScanImageUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ScanResultState())
    val state: StateFlow<ScanResultState> = _state

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun scan(file: File) {
        viewModelScope.launch {
            var token = ""

            _state.value = ScanResultState(isLoading = true)

            readUserSessionUseCase().collect { user ->
                if (user.token?.isNotBlank() == true) {
                    token = user.token

                    _user.value = User(
                        id = user.id,
                        username = user.username,
                        email = user.email,
                        name = user.name,
                        photo = user.photo,
                        token = user.token
                    )
                }

                try {
                    val scan = scanImageUseCase(token, file)
                    scan.collect { result ->
                        when (result) {
                            is Result.Loading -> {
                                _state.value = ScanResultState(isLoading = true)
                            }
                            is Result.Success -> {
                                _state.value = ScanResultState(isLoading = false, labelId = result.data)
                                Log.d("scanCam", result.data.toString())
                            }
                            is Result.Error -> {
                                _state.value = ScanResultState(isLoading = true, error = result.error)
                            }
                        }
                    }
                } catch (exception: HttpException) {
                    _state.value =
                        ScanResultState(isLoading = true, error = exception.message.toString())
                } catch (exception: SocketException) {
                    _state.value = ScanResultState(isLoading = true, error = exception.message.toString())
                } catch (exception: IOException) {
                    _state.value = ScanResultState(isLoading = true, error = exception.message.toString())
                }
            }
        }
    }
}