package io.estevanfick.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("ID")
        val id: Long,
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)