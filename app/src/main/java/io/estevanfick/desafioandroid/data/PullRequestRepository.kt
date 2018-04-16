package io.estevanfick.desafioandroid.data

import io.estevanfick.desafioandroid.data.api.GithubAPI
import io.estevanfick.desafioandroid.data.model.PullRequest
import javax.inject.Inject

class PullRequestRepository @Inject constructor(val api: GithubAPI) {

    suspend fun getPullRequest(user: String, repository: String): List<PullRequest> {
            return api.getPullRequest(user, repository).await()
    }
}