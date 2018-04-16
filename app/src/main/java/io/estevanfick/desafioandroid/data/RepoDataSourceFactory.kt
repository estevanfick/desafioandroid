package io.estevanfick.desafioandroid.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.estevanfick.desafioandroid.data.model.Repo
import javax.inject.Inject


class RepositoryDataSourceFactory @Inject constructor(val source: RepositoryDataSource): DataSource.Factory<Int, Repo>(){
    val liveData = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, Repo> {
        liveData.postValue(source)
        return source
    }
}