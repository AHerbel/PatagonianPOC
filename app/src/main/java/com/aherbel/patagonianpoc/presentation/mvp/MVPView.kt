package com.aherbel.patagonianpoc.presentation.mvp

interface MVPView {

    fun onViewAttached() {}
    fun onViewDetached() {}

    fun showLoading() {}
    fun hideLoading() {}

}