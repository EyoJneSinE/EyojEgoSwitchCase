package com.eniskaner.eyojegoswitchcase.presentation.switch1

import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB


data class MovieDetailState(
    val isLoading : Boolean = false,
    val movieDetails :  GetMovieDetailsFromId? = null,
    val movieCast : CastingForMovieFromTMDB? = null,
    val movieCrew : CastingForMovieFromTMDB? = null,
    val movieGenre : GenresFromTMDB? = null,
    val error : String = ""
)
