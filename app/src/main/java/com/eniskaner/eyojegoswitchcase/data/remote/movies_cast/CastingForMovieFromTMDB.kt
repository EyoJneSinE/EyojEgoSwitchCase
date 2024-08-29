package com.eniskaner.eyojegoswitchcase.data.remote.movies_cast

data class CastingForMovieFromTMDB(
    val cast: List<MovieCast>,
    val crew: List<MovieCrew>,
    val id: Int?
)