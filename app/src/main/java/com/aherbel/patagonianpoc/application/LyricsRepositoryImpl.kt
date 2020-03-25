package com.aherbel.patagonianpoc.application

import com.aherbel.patagonianpoc.application.networking.LyricsResponse
import com.aherbel.patagonianpoc.application.networking.RestClient
import com.aherbel.patagonianpoc.domain.LyricsRepository
import io.reactivex.Single

class LyricsRepositoryImpl : LyricsRepository {

    override fun searchLyric(artist: String, title: String): Single<LyricsResponse> =
        RestClient.lyricsService.searchLyric(artist, title)

}