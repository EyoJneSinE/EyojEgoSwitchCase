package com.eniskaner.eyojegoswitchcase.presentation.switch_details.state

import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails


data class MovieDetailState(
    val isLoading : Boolean = false,
    val movieDetails :  MovieDetails.GetMovieDetailsFromIdUI? = null,
    val movieCast : List<MovieDetails.GetMovieDetailsCastUI>? = emptyList(),
    val movieCrew : List<MovieDetails.GetMovieDetailsCrewUI>? = emptyList(),
    val movieGenre : GenresFromTMDB? = null,
    val error : String = ""
)
