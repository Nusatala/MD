package com.dicoding.nusatalaapp.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.product.get_products.GetProductsUseCase
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
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val readUserSessionUseCase: ReadUserSessionUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            var token = ""

            _state.value = ProductState(isLoading = true)

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
                    val articles = getProductsUseCase(token)
                    articles.collect { result ->
                        when (result) {
                            is Result.Loading -> {
                                _state.value = ProductState(isLoading = true)
                            }
                            is Result.Success -> {
                                _state.value = ProductState(isLoading = false, products = result.data)
                            }
                            is Result.Error -> {
                                _state.value = ProductState(isLoading = true, error = result.error)
                            }
                        }
                    }
                } catch (exception: HttpException) {
                    _state.value =
                       ProductState(isLoading = true, error = exception.message.toString())
                } catch (exception: SocketException) {
                    _state.value = ProductState(isLoading = true, error = exception.message.toString())
                } catch (exception: IOException) {
                    _state.value = ProductState(isLoading = true, error = exception.message.toString())
                }
            }
        }
    }
}