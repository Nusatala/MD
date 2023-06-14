package com.dicoding.nusatalaapp.presentation.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.articles.get_article_by_id.GetArticleByIdUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<DetailArticleState> =
        MutableStateFlow(DetailArticleState())
    val state: StateFlow<DetailArticleState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun getArticleById(id: Int) {
        viewModelScope.launch {
            var token = ""

            _state.value = DetailArticleState(isLoading = true)

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
                    getArticleByIdUseCase(token, id).collect { result ->
                        when (result) {
                            is Result.Loading -> {
                                _state.value = DetailArticleState(isLoading = true)
                            }
                            is Result.Success -> {
                                _state.value =
                                    DetailArticleState(article = result.data, isLoading = false)
                            }
                            is Result.Error -> {
                                _state.value =
                                    DetailArticleState(error = result.error, isLoading = true)
                            }
                        }
                    }
                } catch (exception: HttpException) {
                    _state.value =
                        DetailArticleState(error = exception.message.toString(), isLoading = true)
                } catch (exception: SocketException) {
                    _state.value =
                        DetailArticleState(error = exception.message.toString(), isLoading = true)
                } catch (exception: IOException) {
                    _state.value =
                        DetailArticleState(error = exception.message.toString(), isLoading = true)
                }
            }
        }
    }
}