package com.dicoding.nusatalaapp.presentation.scan.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.ScanResult
import com.dicoding.nusatalaapp.domain.model.Tutorial
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.articles.get_article_by_label_id.GetArticleByLabelIduseCase
import com.dicoding.nusatalaapp.domain.use_case.product.get_product_by_label.GetProductByLabelUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import com.dicoding.nusatalaapp.domain.use_case.tutorial.get_tutorial_by_label_id.GetTutorialByLabelId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

@HiltViewModel
class DetailResultViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val getArticleByLabelIduseCase: GetArticleByLabelIduseCase,
    private val getTutorialByLabelIdUseCase: GetTutorialByLabelId,
    private val getProductsByLabelIdUseCase: GetProductByLabelUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<DetailResultScanState> =
        MutableStateFlow(DetailResultScanState())
    val state: StateFlow<DetailResultScanState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun getArticleByLabelId(labelId: Int) {
        viewModelScope.launch {
            var token = ""

            _state.value = DetailResultScanState(isLoading = true)
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
                    val articleByLabelFlow = getArticleByLabelIduseCase(token, labelId)
                    val tutorialByLabelFlow = getTutorialByLabelIdUseCase(token, labelId)
                    val productsByLabelFlow = getProductsByLabelIdUseCase(token, labelId)

                    combine(
                        articleByLabelFlow,
                        productsByLabelFlow,
                        tutorialByLabelFlow
                    ) { articleByLabelResult, productsByLabelResult, tutorialByLabelResult ->
                        val isLoading =
                            articleByLabelResult is Result.Loading || tutorialByLabelResult is Result.Loading || tutorialByLabelResult is Result.Loading
                        val article = if (articleByLabelResult is Result.Success) articleByLabelResult.data else null
                        val tutorial = if (tutorialByLabelResult is Result.Success) tutorialByLabelResult.data else null
                        val products = if (productsByLabelResult is Result.Success) productsByLabelResult.data else emptyList()

                        _state.value = DetailResultScanState(
                            isLoading = isLoading,
                            article = article,
                            tutorial = tutorial,
                            products = products
                        )
                    }.collect()
                } catch (exception: HttpException) {
                    _state.value =
                        DetailResultScanState(
                            error = exception.message.toString(),
                            isLoading = true
                        )
                } catch (exception: SocketException) {
                    _state.value =
                        DetailResultScanState(
                            error = exception.message.toString(),
                            isLoading = true
                        )
                } catch (exception: IOException) {
                    _state.value =
                        DetailResultScanState(
                            error = exception.message.toString(),
                            isLoading = true
                        )
                }
            }
        }
    }
}