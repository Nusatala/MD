package com.dicoding.nusatalaapp.presentation.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.FieldWithLabel
import com.dicoding.nusatalaapp.presentation.ui.components.TextOnLine
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var usernameFocusState by remember {
        mutableStateOf(false)
    }

    var passwordFocusState by remember {
        mutableStateOf(false)
    }

    var isPasswordVisible by remember {
        mutableStateOf(true)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var usernameError by remember {
        mutableStateOf("")
    }
    var passwordError by remember {
        mutableStateOf("")
    }

    var toastExecuted by rememberSaveable {
        mutableStateOf(false)
    }

    fun isValid(): Boolean {
        var isValid = true

        if (username.isBlank()) {
            usernameError = "Username harus diisi"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Password harus diisi"
            isValid = false
        }

        return isValid
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),

        ) {
        Box(
            modifier = modifier
                .weight(2f)
                .padding(vertical = 24.dp, horizontal = 32.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                text = "Login"
            )
        }
        Column(
            modifier = modifier
                .weight(10f)
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(MaterialTheme.colors.background)
                .padding(vertical = 24.dp, horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FieldWithLabel(
                label = "Username",
                value = username,
                onValueChanged = {
                    username = it
                    usernameError = ""
                },
                placeholder = stringResource(R.string.place_holder_input_username),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "username",
                        tint = if (usernameFocusState) MaterialTheme.colors.primary else if (usernameError.isNotBlank()) Color.Red else Color.Gray
                    )
                },
                trailingIcon = {},
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (usernameFocusState) MaterialTheme.colors.primary else if (usernameError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        usernameFocusState = it.isFocused
                    },
                singleLine = true,
                isError = usernameError.isNotBlank(),
                errorMessage = usernameError
            )
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Password",
                value = password,
                onValueChanged = {
                    password = it
                    passwordError = ""
                },
                placeholder = stringResource(R.string.placeholder_enter_password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "password",
                        tint = if (passwordFocusState) MaterialTheme.colors.primary else if (passwordError.isNotBlank()) Color.Red else Color.Gray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.RemoveRedEye else Icons.Default.VisibilityOff,
                            contentDescription = if (isPasswordVisible) "hide" else "show"
                        )
                    }
                },
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (passwordFocusState) MaterialTheme.colors.primary else if (passwordError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        passwordFocusState = it.isFocused
                    },
                singleLine = true,
                keyboardType = KeyboardType.Password,
                visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                isError = passwordError.isNotBlank(),
                errorMessage = passwordError
            )
            Text(
                text = "Lupa password?",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.End,
                color = MaterialTheme.colors.primaryVariant
            )
            ButtonBase(
                text = "Login",
                modifier = Modifier,
                isLoading = isLoading,
                onClick = {
                    if (isValid() && !isLoading) {
                        isLoading = true
                        toastExecuted = false
                        viewModel.login(username, password)
                    }
                }
            )

            LaunchedEffect(Unit) {
                viewModel.state.collect { authState ->
                    if (authState.user.id != null) {
                        isLoading = false
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route) {
                            launchSingleTop = true
                        }
                    }

                    if (authState.error.isNotEmpty() && !toastExecuted) {
                        isLoading = false
                        toastExecuted = true
                        Toast.makeText(context, authState.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            Row {
                Text(text = "Belum punya akun?")
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "Sign up",
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Register.route)
                    })
            }
            TextOnLine(
                text = "atau login dengan",
                modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "google logo"
            )
        }
    }
}

