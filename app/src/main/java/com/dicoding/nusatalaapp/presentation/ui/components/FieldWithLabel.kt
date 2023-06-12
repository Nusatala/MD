package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography

@Composable
fun FieldWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    placeholder: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    singleLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMessage: String = "",
    isError: Boolean = false,
    onValueChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Start,
        )
        TextFieldBase(
            value = value,
            onValueChanged = onValueChanged,
            modifier = modifier.fillMaxWidth(),
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            keyboardType = keyboardType,
            visualTransformation = visualTransformation,
        )
        AnimatedVisibility(visible = isError) {
            Text(
                text = errorMessage,
                style = InfoTypography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                textAlign = TextAlign.Start,
            )
        }
    }
}