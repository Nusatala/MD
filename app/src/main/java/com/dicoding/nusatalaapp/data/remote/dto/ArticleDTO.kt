package com.dicoding.nusatalaapp.data.remote.dto

import com.dicoding.nusatalaapp.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    val id: Int? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("label_id")
    val labelId: Int? = null,

    @field:SerializedName("image_id")
    val imageId: Int? = null,

    val image: ImageDTO? = null,

    val title: String? = null,

    val history: String? = null,

    @field:SerializedName("bahan_pembuatan")
    val bahanPembuatan: String? = null,

    @field:SerializedName("asal_daerah")
    val asalDaerah: String? = null,

    @field:SerializedName("tutorial_id")
    val tutorialId: Int? = null,

    val sources: String? = null,

    val views: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,
)

fun ArticleDTO.toModel(): Article {
    return Article(
        id = id,
        title = title,
        body = history,
        image = image?.toModel(),
        views = views,
    )
}
