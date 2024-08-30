package com.eniskaner.eyojegoswitchcase.presentation.switch_details.model

interface MovieDetailsDisplayItem {

    fun type(): Int

    companion object {
        const val TYPE_MOVIE_DETAILS = 0
        const val TYPE_MOVIE_CAST_LIST = 1
        const val TYPE_MOVIE_CREW_LIST = 2
        const val TYPE_MOVIE_CAST = 3
        const val TYPE_MOVIE_CREW = 4
    }
}