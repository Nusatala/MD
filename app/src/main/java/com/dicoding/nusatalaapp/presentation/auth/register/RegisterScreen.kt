package com.dicoding.nusatalaapp.presentation.auth.register

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.FieldWithLabel
import com.dicoding.nusatalaapp.presentation.ui.components.TextOnLine
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)

    val context = LocalContext.current

    var fullName by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfirmation by remember {
        mutableStateOf("")
    }

    var fullNameFocusState by remember {
        mutableStateOf(false)
    }

    var usernameFocuseState by remember {
        mutableStateOf(false)
    }

    var emailFocusState by remember {
        mutableStateOf(false)
    }

    var passwordFocusState by remember {
        mutableStateOf(false)
    }

    var passwordConfirmationState by remember {
        mutableStateOf(false)
    }

    var isPasswordVisible by remember {
        mutableStateOf(true)
    }

    var isPasswordConfirmationVisible by remember {
        mutableStateOf(true)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var fullNameError by remember {
        mutableStateOf("")
    }

    var usernameError by remember {
        mutableStateOf("")
    }

    var emailError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var passwordConfirmationError by remember {
        mutableStateOf("")
    }

    fun isValid(): Boolean {
        var isValid = true

        if (fullName.isBlank()) {
            fullNameError = "Nama lengkap harus diisi"
            isValid = false
        }

        if (username.isBlank()) {
            usernameError = "Username harus diisi"
            isValid = false
        }

        if (email.isBlank()) {
            emailError = "Email haris diisi"
            isValid = false
        }

        if (email.isNotEmpty() && !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Email tidak sesuai"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Password harus diisi"
            isValid = false
        }

        if (passwordConfirmation.isBlank()) {
            passwordConfirmationError = "Konfirmasi password harus diisi"
            isValid = false
        }

        if (passwordConfirmation != password) {
            passwordConfirmationError = "Tidak sesuai dengan password"
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
                text = "Register"
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
                label = "Nama Lengkap",
                value = fullName,
                onValueChanged = {
                    fullName = it
                    fullNameError = ""
                },
                placeholder = stringResource(R.string.placeholder_enter_fullname),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "nama",
                        tint = if (fullNameFocusState) MaterialTheme.colors.primary else if (fullNameError.isNotBlank()) Color.Red else Color.Gray
                    )
                },
                trailingIcon = {},
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (fullNameFocusState) MaterialTheme.colors.primary else if (fullNameError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        fullNameFocusState = it.isFocused
                    },
                singleLine = true,
                isError = fullNameError.isNotBlank(),
                errorMessage = fullNameError
            )
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Username",
                value = username,
                onValueChanged = {
                    username = it
                    usernameError = ""
                },
                placeholder = stringResource(R.string.placeholder_enter_username),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "username",
                        tint = if (usernameFocuseState) MaterialTheme.colors.primary else if (usernameError.isNotBlank()) Color.Red else Color.Gray,
                    )
                },
                trailingIcon = {},
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (usernameFocuseState) MaterialTheme.colors.primary else if (usernameError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        usernameFocuseState = it.isFocused
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
                label = "Email",
                value = email,
                onValueChanged = {
                    email = it
                    emailError = ""
                },
                placeholder = stringResource(R.string.placeholder_enter_email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Mail,
                        contentDescription = "email",
                        tint = if (emailFocusState) MaterialTheme.colors.primary else if (emailError.isNotBlank()) Color.Red else Color.Gray,
                    )
                },
                trailingIcon = {},
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (emailFocusState) MaterialTheme.colors.primary else if (emailError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        emailFocusState = it.isFocused
                    },
                singleLine = true,
                keyboardType = KeyboardType.Email,
                isError = emailError.isNotBlank(),
                errorMessage = emailError
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
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password",
                        tint = if (passwordFocusState) MaterialTheme.colors.primary else if (passwordError.isNotBlank()) Color.Red else Color.Gray,
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
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Konfirmasi Password",
                value = passwordConfirmation,
                onValueChanged = {
                    passwordConfirmation = it
                    passwordConfirmationError = ""
                },
                placeholder = stringResource(R.string.placeholder_enter_password_confirmation),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password confirmation",
                        tint = if (passwordConfirmationState) MaterialTheme.colors.primary else if (passwordConfirmationError.isNotBlank()) Color.Red else Color.Gray,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordConfirmationVisible = !isPasswordConfirmationVisible
                    }) {
                        Icon(
                            imageVector = if (isPasswordConfirmationVisible) Icons.Default.RemoveRedEye else Icons.Default.VisibilityOff,
                            contentDescription = if (isPasswordConfirmationVisible) "hide" else "show"
                        )
                    }
                },
                modifier = Modifier
                    .border(
                        1.dp,
                        color = if (passwordConfirmationState) MaterialTheme.colors.primary else if (passwordConfirmationError.isNotBlank()) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged {
                        passwordConfirmationState = it.isFocused
                    },
                keyboardType = KeyboardType.Password,
                visualTransformation = if (isPasswordConfirmationVisible) PasswordVisualTransformation() else VisualTransformation.None,
                singleLine = true,
                isError = passwordConfirmationError.isNotBlank(),
                errorMessage = passwordConfirmationError
            )
            ButtonBase(
                text = "Sign Up",
                modifier = Modifier,
                isLoading = isLoading,
                onClick = {
                    if (isValid() && !isLoading) {
                        isLoading = true
                        viewModel.register(
                            name = fullName,
                            username = username,
                            email = email,
                            password = password
                        )
                    }
                }
            )

            LaunchedEffect(Unit) {
                viewModel.state.collect { authState ->
                    if (authState.user.id != null) {
                        isLoading = false
                        Toast.makeText(context, "Register success", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route) {
                            launchSingleTop = true
                        }
                    }
                    if (authState.error.isNotEmpty()) {
                        isLoading = false
                        Toast.makeText(context, authState.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Row {
                Text(text = "Sudah punya akun?")
                Spacer(modifier = modifier.size(4.dp))
                Text(text = "Sign In",
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    })
            }
            TextOnLine(
                text = "atau login dengan", modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "google logo"
            )
        }
    }
}