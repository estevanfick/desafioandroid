package io.estevanfick.desafioandroid.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class PullRequest(
        @SerializedName("ID")
        val id: Int,
        @SerializedName("html_url")
        val url: String,
        val title: String,
        val body: String,
        @SerializedName("created_at")
        val date: Date,
        val user: User
)