package com.dicoding.nusatalaapp.presentation.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.FieldOutlineWithLabelWrapContent
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase
import com.dicoding.nusatalaapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarBase(title = "Setting", filled = true, onBackClicked = navigateBack)
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = "setting",
                    modifier = modifier
                        .size(128.dp),
                )
            }

            var expanded by remember { mutableStateOf(false) }
            var selectedOption by remember { mutableStateOf("Option 1") }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Bahasa")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { expanded = !expanded }
                        .border(
                            1.dp,
                            MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(text = selectedOption)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon",
                        modifier = modifier.align(Alignment.CenterEnd),
                        tint = MaterialTheme.colors.primary
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = "Bahasa Indonesia"
                            expanded = false
                        }
                    ) {
                        Text("Bahasa Indonesia")
                    }
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = "English"
                            expanded = false
                        }
                    ) {
                        Text("English")
                    }
                }
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Notifikasi")
                Switch(
                    checked = true,
                    onCheckedChange = { },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.primary,
                        checkedTrackColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                        uncheckedThumbColor = MaterialTheme.colors.onSurface,
                        uncheckedTrackColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    )
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Theme")
                Switch(
                    checked = false,
                    onCheckedChange = { },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.primary,
                        checkedTrackColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                        uncheckedThumbColor = MaterialTheme.colors.onSurface,
                        uncheckedTrackColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    )
                )
            }
        }
    }
}