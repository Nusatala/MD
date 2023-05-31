package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarBase(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = modifier.padding(horizontal = 8.dp)
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 48.dp)
            .shadow(1.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBarBase(query = "", onQueryChange = {})
}