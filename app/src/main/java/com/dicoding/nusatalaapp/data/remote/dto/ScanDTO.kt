package com.dicoding.nusatalaapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ScanDTO(

	@field:SerializedName("label_id")
	val labelId: Int? = null
)

fun ScanDTO.toModel(): Int {
	return labelId ?: -1
}
