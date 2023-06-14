package com.dicoding.nusatalaapp.domain.model

import android.content.res.Resources

data class Article(
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
    val image: Image? = null,
    val views: Int? =  null,
    val sources: String? = null,
)
