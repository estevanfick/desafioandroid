package io.estevanfick.desafioandroid.data

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import io.estevanfick.desafioandroid.data.model.Repo


class RepoResponse (
        val networkStatus: LiveData<NetworkStatus>,
        val list: LiveData<PagedList<Repo>>
)