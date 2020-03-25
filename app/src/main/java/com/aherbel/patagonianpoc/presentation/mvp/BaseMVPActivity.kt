package com.aherbel.patagonianpoc.presentation.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMVPActivity<T: MVPRxPresenter<V>, V: MVPView>: AppCompatActivity() {

    protected lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    abstract fun createPresenter(): T
}