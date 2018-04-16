package io.estevanfick.desafioandroid.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import io.estevanfick.desafioandroid.data.api.GithubAPI
import io.estevanfick.desafioandroid.data.model.Repo
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class RepositoryDataSource @Inject constructor(val api: GithubAPI): PageKeyedDataSource<Int, Repo>(){

    val networkState = MutableLiveData<NetworkStatus>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        networkState.postValue(NetworkStatus.RUNNING)
        async {
            try {
                val list = api.getJavaRepo(1).await()
                networkState.postValue(NetworkStatus.SUCCESS)
                callback.onResult(list.list, 0, 2)
            } catch (e: Exception) {
                networkState.postValue(NetworkStatus.FAILED)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        networkState.postValue(NetworkStatus.RUNNING)
        async {
            try {
                val list = api.getJavaRepo(params.key).await()
                networkState.postValue(NetworkStatus.SUCCESS)
                callback.onResult(list.list, params.key + 1)
            } catch (e: Exception) {
                networkState.postValue(NetworkStatus.FAILED)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {

    }

}