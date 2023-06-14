package com.dicoding.nusatalaapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.destroy_user_session.DestroyUserSessionUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val destroyUserSessionUseCase: DestroyUserSessionUseCase,
) : ViewModel() {
    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _logoutStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val logoutStatus: StateFlow<Boolean> = _logoutStatus.asStateFlow()

    init {
        getUserSession()
    }

    fun getUserSession() {
        viewModelScope.launch {
            readUserSessionUseCase().collect { user ->
                if (user.token?.isNotBlank() == true) {
                    _user.value = User(
                        id = user.id,
                        username = user.username,
                        email = user.email,
                        name = user.name,
                        photo = user.photo,
                        token = user.token
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            val logoutStatus = destroyUserSessionUseCase()
            _logoutStatus.value = logoutStatus
        }
    }
}