package com.dicoding.nusatalaapp.data.remote.dto

import com.dicoding.nusatalaapp.domain.model.Tutorial
import com.google.gson.annotations.SerializedName

data class TutorialDTO(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("label_id")
	val labelId: Int? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,
)

fun TutorialDTO.toModel(): Tutorial {
	return Tutorial(
		id = id,
		labelId = labelId,
		link = link
	)
}
