package com.aherbel.patagonianpoc.presentation.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class MVPRxPresenter<T> where T: MVPView {

    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        view.onViewAttached()
    }

    fun detachView() {
        if (compositeDisposable?.isDisposed == false) {
            compositeDisposable?.dispose()
        }
        compositeDisposable = null

        view?.onViewDetached()
        view = null
    }

    fun launch(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

}