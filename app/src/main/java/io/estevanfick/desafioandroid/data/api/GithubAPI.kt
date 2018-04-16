package io.estevanfick.desafioandroid.data.api

import io.estevanfick.desafioandroid.data.model.PullRequest
import io.estevanfick.desafioandroid.data.model.RepoList
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun getJavaRepo(@Query("page") page: Int): Deferred<RepoList>

    @GET("repos/{user}/{repo}/pulls")
    fun getPullRequest(@Path("user") user: String, @Path("repo") repo: String): Deferred<List<PullRequest>>
}