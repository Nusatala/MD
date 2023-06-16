package com.dicoding.nusatalaapp.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.use_case.product.get_product_by_id.GetProductByIdUseCase
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
class ProductDetailViewModel @Inject constructor(
    private val readUserSessionUseCase: ReadUserSessionUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<ProductDetailState> =
        MutableStateFlow(ProductDetailState())
    val state: StateFlow<ProductDetailState> = _state.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            var token = ""

            _state.value = ProductDetailState(isLoading = true)

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
                    getProductByIdUseCase(token, id).collect { result ->
                        when (result) {
                            is Result.Loading -> {
                                _state.value = ProductDetailState(isLoading = true)
                            }
                            is Result.Success -> {
                                _state.value =
                                    ProductDetailState(product = result.data, isLoading = false)
                            }
                            is Result.Error -> {
                                _state.value =
                                    ProductDetailState(error = result.error, isLoading = true)
                            }
                        }
                    }
                } catch (exception: HttpException) {
                    _state.value =
                        ProductDetailState(error = exception.message.toString(), isLoading = true)
                } catch (exception: SocketException) {
                    _state.value =
                        ProductDetailState(error = exception.message.toString(), isLoading = true)
                } catch (exception: IOException) {
                    _state.value =
                        ProductDetailState(error = exception.message.toString(), isLoading = true)
                }
            }
        }
    }
}