package io.estevanfick.desafioandroid.data

import android.arch.paging.LivePagedListBuilder
import java.util.concurrent.Executors
import javax.inject.Inject


class RepoRepository @Inject constructor(val dataSource: RepositoryDataSourceFactory) {

    fun getRepoList(): RepoResponse {
        val pagedList = LivePagedListBuilder(dataSource, 10)
                .setFetchExecutor(Executors.newFixedThreadPool(3))
        return RepoResponse(dataSource.source.networkState, pagedList.build())
    }
}