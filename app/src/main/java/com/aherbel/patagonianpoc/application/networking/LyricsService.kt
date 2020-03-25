package com.aherbel.patagonianpoc.application.networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsService {

    @GET("v1/{artist}/{title}")
    fun searchLyric(
        @Path("artist") artist: String,
        @Path("title") title: String
    ): Single<LyricsResponse>

}