package io.estevanfick.desafioandroid.di

import dagger.Component
import io.estevanfick.desafioandroid.data.api.ApiManager
import io.estevanfick.desafioandroid.ui.pullrequestlist.PullRequestActivity
import io.estevanfick.desafioandroid.ui.repolist.RepoActivity

@Component (modules = [ApiManager::class])
interface AppComponent {
    fun inject(target: RepoActivity)
    fun inject(target: PullRequestActivity)
}