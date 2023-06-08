package com.dicoding.nusatalaapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(

	@field:SerializedName("google_id")
	val googleId: Any? = null,

	val password: String? = null,

	@field:SerializedName("google_secret")
	val googleSecret: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("role_id")
	val roleId: Int? = null,

	val name: String? = null,

	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	val id: Int? = null,

	val email: String? = null,

	val username: String? = null,

	val token: String? = null
)
