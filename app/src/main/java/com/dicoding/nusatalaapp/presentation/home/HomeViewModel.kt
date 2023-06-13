package com.dicoding.nusatalaapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.articles.get_articles_by_views.GetArticlesByViewsUseCase
import com.dicoding.nusatalaapp.domain.use_case.articles.get_latest_articles.GetLatestArticlesUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val getArticlesByViewsUseCase: GetArticlesByViewsUseCase,
    private val getLatestArticlesUseCase: GetLatestArticlesUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            var token = ""

            _state.value = HomeState(isLoading = true)

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
                    val articlesByViewsFlow = getArticlesByViewsUseCase(token = token)
                    val latestArticlesFlow = getLatestArticlesUseCase(token = token)

                    articlesByViewsFlow.combine(latestArticlesFlow) { articlesByViewsResult, latestArticlesResult ->
                        val isLoading =
                            articlesByViewsResult is Result.Loading || latestArticlesResult is Result.Loading
                        val articles =
                            if (articlesByViewsResult is Result.Success) articlesByViewsResult.data else emptyList()
                        val latestArticles =
                            if (latestArticlesResult is Result.Success) latestArticlesResult.data else emptyList()

                        _state.value = HomeState(
                            isLoading = isLoading,
                            articles = articles,
                            latestArticles = latestArticles
                        )
                    }.collect()
                } catch (exception: HttpException) {
                    _state.value =
                        HomeState(isLoading = true, error = exception.message.toString())
                } catch (exception: SocketException) {
                    _state.value = HomeState(isLoading = true, error = exception.message.toString())
                } catch (exception: IOException) {
                    _state.value = HomeState(isLoading = true, error = exception.message.toString())
                }
            }
        }
    }
}