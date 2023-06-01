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
    modifier: Modifier = Modifier,
) {
    val faqs = listOf(
        Faq(
            question = "What is the purpose of this app?",
            answer = "The purpose of this app is to provide users with a seamless and intuitive user experience while offering a wide range of features and functionalities."
        ),
        Faq(
            question = "How can I create an account?",
            answer = "To create an account, navigate to the app's homepage and click on the 'Sign Up' button. Fill in the required information, such as your name, email address, and password, and then click 'Create Account' to complete the registration process."
        ),
        Faq(
            question = "Can I change my password?",
            answer = "Yes, you can change your password by accessing the 'Account Settings' section in the app. From there, you can update your password by providing your current password and entering a new password of your choice."
        ),
        Faq(
            question = "Are there any subscription plans available?",
            answer = "Yes, we offer a variety of subscription plans tailored to meet different user needs. Our plans include a range of features and benefits, such as ad-free browsing, exclusive content, and priority customer support. You can choose a subscription plan that best fits your requirements."
        ),
        Faq(
            question = "How can I contact customer support?",
            answer = "For any questions, concerns, or assistance, you can contact our customer support team through our dedicated helpline at +123456789 or send us an email at support@example.com. Our support representatives are available to assist you during our business hours from Monday to Friday."
        )
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarBase(title = "Frequently Asked Question", onBackClicked = {})
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
                Image(
                    painter = painterResource(id = R.drawable.faq),
                    contentDescription = "faq",
                    modifier = modifier
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
                .clickable { }
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = question)
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