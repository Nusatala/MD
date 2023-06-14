package com.dicoding.nusatalaapp.presentation.article

import com.dicoding.nusatalaapp.domain.model.Article

data class DetailArticleState(
    val isLoading: Boolean = false,
    val article: Article? = null,
    val error: String? = null
)
