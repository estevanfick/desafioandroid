package io.estevanfick.desafioandroid.data.model

import com.google.gson.annotations.SerializedName

data class Repo (
        @SerializedName("ID")
        val id: Long,
        val name: String,
        val description: String,
        val owner: User,
        @SerializedName("watchers")
        val stars: Int,
        @SerializedName("forks_count")
        val forks: Int
)