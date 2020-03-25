package com.aherbel.patagonianpoc.application

import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class POCApplication : Application() {

    companion object {
        private val repositoryFactory = RepositoryFactory()

        fun getRepository(repositoryType: RepositoryFactory.Types) = repositoryFactory.getRepository(repositoryType)
    }

    override fun onCreate() {
        super.onCreate()

        /*RxJavaPlugins.setErrorHandler {
            Log.e("ERROR", it.message, it)
        }*/
    }
}