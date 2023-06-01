package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldBase(
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "leading icon",
                )
            }
        },
        trailingIcon = {
            if (trailingIcon != null)
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "trailing icon",
                )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
@Preview(showBackground = true)
fun TextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        TextFieldBase(
            value = "",
            onValueChanged = {},
            placeholder = "Masukan teks",
            leadingIcon = Icons.Filled.Person,
            trailingIcon = Icons.Filled.Visibility,
        )
    }
}