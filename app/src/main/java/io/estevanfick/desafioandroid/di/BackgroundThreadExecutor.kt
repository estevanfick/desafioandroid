package io.estevanfick.desafioandroid.di

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class BackgroundThreadExecutor @Inject constructor(): Executor {
    private val executorService = Executors.newFixedThreadPool(3)

    override fun execute(command: Runnable) {
        executorService.execute(command)
    }
}