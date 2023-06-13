package com.dicoding.nusatalaapp.presentation.article

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.model.User

data class ArticleState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String = ""
)
