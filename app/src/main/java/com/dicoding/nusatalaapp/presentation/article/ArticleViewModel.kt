package com.dicoding.nusatalaapp.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.articles.get_articles.GetArticlesUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase,
    private val readUserSessionUseCase: ReadUserSessionUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val state: StateFlow<ArticleState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            var token = ""

            _state.value = ArticleState(isLoading = true)

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
                    val articles = getArticles(token)
                    articles.collect { result ->
                        when (result) {
                            is Result.Loading -> {
                                _state.value = ArticleState(isLoading = true)
                            }
                            is Result.Success -> {
                                _state.value = ArticleState(isLoading = false, articles = result.data)
                            }
                            is Result.Error -> {
                                _state.value = ArticleState(isLoading = true, error = result.error)
                            }
                        }
                    }
                } catch (exception: HttpException) {
                    _state.value =
                        ArticleState(isLoading = true, error = exception.message.toString())
                } catch (exception: SocketException) {
                    _state.value = ArticleState(isLoading = true, error = exception.message.toString())
                } catch (exception: IOException) {
                    _state.value = ArticleState(isLoading = true, error = exception.message.toString())
                }
            }
        }
    }
}