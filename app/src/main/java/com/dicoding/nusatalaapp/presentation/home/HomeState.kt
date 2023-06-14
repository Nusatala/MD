package com.dicoding.nusatalaapp.presentation.home

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val latestArticles: List<Article> = emptyList(),
    val articles: List<Article> = emptyList(),
    val error: String = ""
)