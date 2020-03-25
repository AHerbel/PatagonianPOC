package com.aherbel.patagonianpoc.presentation.screens.main

import com.aherbel.patagonianpoc.application.POCApplication
import com.aherbel.patagonianpoc.application.RepositoryFactory
import com.aherbel.patagonianpoc.application.networking.LyricsResponse
import com.aherbel.patagonianpoc.domain.Lyric
import com.aherbel.patagonianpoc.domain.LyricsRepository
import com.aherbel.patagonianpoc.presentation.mvp.MVPRxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainMVPPresenter : MVPRxPresenter<MainMVPView>() {

    companion object {
        private const val NO_LYRICS_FOUND_MESSAGE = "No lyrics found"
        private const val IT_SEEMS_THERE_IS_NO_INTERNET = "It seems there is no internet. Check the internet connection please."
    }

    private var lyricsSearchHistory: ArrayList<Lyric> = ArrayList()

    private val lyricsRepository: LyricsRepository =
        POCApplication.getRepository(RepositoryFactory.Types.LYRICS)

    fun searchLyric(artist: String, title: String) {
        view?.showLoading()
        launch(
            lyricsRepository.searchLyric(artist, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.hideLoading() }
                .subscribe({ response: LyricsResponse? ->
                    val lyrics = response?.lyrics.orEmpty()
                    if (lyrics.isEmpty()) {
                        view?.onError(NO_LYRICS_FOUND_MESSAGE)
                        return@subscribe
                    }

                    val lyric = Lyric(artist, title, lyrics)
                    lyricsSearchHistory.add(lyric)
                    view?.onLyricRetrieved(lyric)
                }, { error: Throwable? ->
                    val errorMessage = when {
                        error is HttpException && error.code() == 404 -> NO_LYRICS_FOUND_MESSAGE
                        error is UnknownHostException || error is SocketTimeoutException -> IT_SEEMS_THERE_IS_NO_INTERNET
                        else -> error?.message.orEmpty()
                    }

                    if (errorMessage.isEmpty()) {
                        view?.onError(NO_LYRICS_FOUND_MESSAGE)
                    } else {
                        view?.onError(errorMessage)
                    }
                })
        )
    }

    fun clickSearchHistory() {
        view?.onSearchHistoryClicked(lyricsSearchHistory)
    }

}