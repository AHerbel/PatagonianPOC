package com.aherbel.patagonianpoc.presentation.screens.main

import com.aherbel.patagonianpoc.domain.Lyric
import com.aherbel.patagonianpoc.presentation.mvp.MVPView

interface MainMVPView: MVPView {

    fun onLyricRetrieved(lyric: Lyric?)
    fun onSearchHistoryClicked(lyricsSearchHistory: ArrayList<Lyric>)
    fun onError(errorMessage: String)

}