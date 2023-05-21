package com.dicoding.nusatalaapp.presentation.ui.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.FieldWithLabel
import com.dicoding.nusatalaapp.presentation.ui.components.TextFieldBase
import com.dicoding.nusatalaapp.presentation.ui.components.TextOnLine
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)
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
            var email by remember {
                mutableStateOf("")
            }
            var fullName by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            var passwordConfirmation by remember {
                mutableStateOf("")
            }
            FieldWithLabel(
                label = "Email",
                value = email,
                onValueChanged  = {
                    email = it
                },
                placeholder = stringResource(R.string.placeholder_enter_email),
                leadingIcon = Icons.Filled.Email,
                modifier = modifier,
            )
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Nama Lengkap",
                value = fullName,
                onValueChanged  = {
                    fullName = it
                },
                placeholder = stringResource(R.string.placeholder_enter_fullname),
                leadingIcon = Icons.Filled.Person,
                modifier = modifier,
            )
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Password",
                value = password,
                onValueChanged  = {
                    password = it
                },
                placeholder = stringResource(R.string.placeholder_enter_password),
                leadingIcon = Icons.Filled.Lock,
                trailingIcon = Icons.Filled.Visibility,
                modifier = modifier,
            )
            Spacer(
                modifier = modifier.size(
                    16.dp
                )
            )
            FieldWithLabel(
                label = "Konfirmasi Password",
                value = passwordConfirmation,
                onValueChanged  = {
                    passwordConfirmation = it
                },
                placeholder = stringResource(R.string.placeholder_enter_password_confirmation),
                leadingIcon = Icons.Filled.Lock,
                trailingIcon = Icons.Filled.Visibility,
                modifier = modifier,
            )

            ButtonBase(
                text = "Sign Up",
                modifier = modifier,
                onClick = {}
            )
            Row {
                Text(text = "Sudah punya akun?")
                Spacer(modifier = modifier.size(4.dp))
                Text(text = "Sign In", color = MaterialTheme.colors.primaryVariant)
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