package io.estevanfick.desafioandroid.data.model

import com.google.gson.annotations.SerializedName


data class RepoList(
        @SerializedName("items")
        val list: List<Repo>
)