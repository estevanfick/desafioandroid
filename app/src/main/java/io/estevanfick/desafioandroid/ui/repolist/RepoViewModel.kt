package io.estevanfick.desafioandroid.ui.repolist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import io.estevanfick.desafioandroid.data.NetworkStatus
import io.estevanfick.desafioandroid.data.RepoRepository
import io.estevanfick.desafioandroid.data.model.Repo
import javax.inject.Inject


class RepoViewModel @Inject constructor(val repoRepository: RepoRepository): ViewModel() {

    var response = repoRepository.getRepoList()
    val pagedList: LiveData<PagedList<Repo>> = response.list
    val networkStatus: LiveData<NetworkStatus> = response.networkStatus

}