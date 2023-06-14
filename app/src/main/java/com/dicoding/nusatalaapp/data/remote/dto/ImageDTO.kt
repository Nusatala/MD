package com.dicoding.nusatalaapp.data.remote.dto

import com.dicoding.nusatalaapp.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageDTO(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("label_id")
	val labelId: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,
)

fun ImageDTO.toModel(): Image {
	return Image(
		id = id,
		userId = userId,
		labelId = labelId,
		image = image
	)
}
