package com.dicoding.nusatalaapp.data.remote.dto

import com.dicoding.nusatalaapp.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductDTO(
	val thumbnail: String? = null,
	@field:SerializedName("updated_at")
	val updatedAt: String? = null,
	@field:SerializedName("user_id")
	val userId: Int? = null,
	val price: String? = null,
	val name: String? = null,
	val link: String? = null,
	val rating: Float? = null,
	val description: String? = null,
	@field:SerializedName("created_at")
	val createdAt: String? = null,
	val id: Int? = null,
	@field:SerializedName("label_id")
	val labelId: Int? = null,
)

fun ProductDTO.toModel(): Product {
	return Product(
		id = id ?: -1,
		thumbnail = thumbnail ?: "",
		name = name ?: "",
		price = price ?: "0",
		description = description ?: "",
		rating = rating ?: 0f,
		link = link ?: "",
	)
}