package com.aherbel.patagonianpoc.domain

import com.aherbel.patagonianpoc.application.networking.LyricsResponse
import io.reactivex.Single

interface LyricsRepository {

    fun searchLyric(artist: String, title: String): Single<LyricsResponse>
}