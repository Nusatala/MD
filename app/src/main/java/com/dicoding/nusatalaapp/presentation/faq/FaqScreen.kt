package com.dicoding.nusatalaapp.presentation.faq

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.domain.model.Faq
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FaqScreen(
    navigateBack: () -> Unit,
) {
    val faqs = listOf(
        Faq(
            question = "Apa itu Nusatala?",
            answer = "Nusatala adalah sebuah aplikasi yang dapat mendeteksi gambar alat musik tradisional menggunakan teknologi image recognition."
        ),
        Faq(
            question = "Bagaimana cara menggunakan Nusatala?",
            answer = "Unduh dan instal aplikasi Nusatala, lalu buka aplikasi tersebut. Ambil gambar alat musik tradisional Indonesia, dan aplikasi akan memberikan informasi terkait alat musik tersebut."
        ),
        Faq(
            question = "Apakah Aplikasi Nusatala mencakup semua alat musik tradisional Indonesia?",
            answer = "Untuk saat ini, Nusatala hanya dapat mengenali beberapa alat musik tradisional saja. Tim pengembang terus berupaya untuk memperluas database alat musik tradisional yang terdaftar dalam aplikasi agar mencakup lebih banyak jenis alat musik di masa mendatang."
        )
    )

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBarBase(title = "Frequently Asked Question", filled = true, onBackClicked = navigateBack)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.faq),
                    contentDescription = "faq",
                    modifier = Modifier
                        .size(128.dp),
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 56.dp)
            ) {
                items(faqs, key = { it.question }) {
                    FaqCardItem(question = it.question, answer = it.answer)
                }
            }
        }
    }
}

@Composable
fun FaqCardItem(
    question: String,
    answer: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = question, modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { expanded = !expanded },
                ) {
                    val icon: ImageVector = if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }
                    Icon(imageVector = icon, contentDescription = "Expand/Collapse")
                }
            }
            AnimatedVisibility(visible = expanded) {
                Text(text = answer)
            }
        }
    }
}