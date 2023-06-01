package com.dicoding.nusatalaapp.presentation.account

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.FieldOutlineWithLabelWrapContent
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditAccountScreen(
    imageUrl: String,
    name: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarBase(title = "Edit Profile", onBackClicked = {})
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = modifier
                        .size(128.dp)
                        .clip(RoundedCornerShape(50)),
                    contentScale = ContentScale.Crop,
                )
                Surface(
                    modifier = modifier
                        .offset(x = 54.dp, y = (-32).dp),
                    elevation = 2.dp,
                    shape = RoundedCornerShape(50),
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = modifier
                            .clickable { }
                            .padding(4.dp)
                            .size(18.dp)
                    )
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = modifier.weight(1f)) {
                    FieldOutlineWithLabelWrapContent(
                        label = "Nama depan",
                        value = "",
                        onValueChanged = {})
                }
                Box(modifier = modifier.weight(1f)) {
                    FieldOutlineWithLabelWrapContent(
                        label = "Nama belakang",
                        value = "",
                        onValueChanged = {})
                }
            }

            FieldOutlineWithLabelWrapContent(
                label = "Email",
                value = "",
                onValueChanged = {},
                modifier = modifier.fillMaxWidth()
            )

            ButtonBase(text = "Simpan", onClick = {})
        }
    }
}