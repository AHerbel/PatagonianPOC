package com.aherbel.patagonianpoc.application

class RepositoryFactory {

    enum class Types {
        LYRICS
    }

    fun getRepository(repositoryType: Types) = when(repositoryType) {
        Types.LYRICS -> LyricsRepositoryImpl()
    }

}