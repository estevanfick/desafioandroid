package io.estevanfick.desafioandroid.ui.pullrequestlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.estevanfick.desafioandroid.data.NetworkStatus
import io.estevanfick.desafioandroid.data.PullRequestRepository
import io.estevanfick.desafioandroid.data.model.PullRequest
import kotlinx.coroutines.experimental.async


class PullRequestViewModel(val repository: PullRequestRepository): ViewModel(){

    var user: String? = null
    var repo: String? = null
    var pullRequests = MutableLiveData<List<PullRequest>>()
    val networkStatus = MutableLiveData<NetworkStatus>()


    fun loadData(user: String, repo: String) {
        if (this.user != user || this.repo != repo){
            this.user = user
            this.repo = repo
            async {
                try {
                    networkStatus.postValue(NetworkStatus.RUNNING)
                    pullRequests.postValue(repository.getPullRequest(user, repo))
                    networkStatus.postValue(NetworkStatus.SUCCESS)
                } catch (e: Exception) {
                    networkStatus.postValue(NetworkStatus.FAILED)
                }
            }
        }
    }
}