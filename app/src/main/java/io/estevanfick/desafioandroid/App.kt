package io.estevanfick.desafioandroid

import android.app.Application
import io.estevanfick.desafioandroid.data.api.ApiManager
import io.estevanfick.desafioandroid.di.AppComponent
import io.estevanfick.desafioandroid.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .apiManager(ApiManager())
                .build()
    }
}