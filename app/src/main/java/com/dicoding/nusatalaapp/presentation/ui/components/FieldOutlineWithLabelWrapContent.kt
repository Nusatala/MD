package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FieldOutlineWithLabelWrapContent(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = label,
            modifier = Modifier
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Start,
        )
        TextField(
            value = value,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = modifier
                .heightIn(min = 48.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colors.primary, shape = RoundedCornerShape(12.dp)),
        )
    }
}