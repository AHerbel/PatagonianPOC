package com.aherbel.patagonianpoc.domain

import java.io.Serializable

data class Lyric(
    val artist: String,
    val title: String,
    val lyric: String
) : Serializable