package com.eniskaner.eyojegoswitchcase.domain.mapper


import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.MovieCast
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.MovieCrew
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCastUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCrewUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCastListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCrewListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsFromIdUI

fun GetMovieDetailsFromId.toMovieDetailsUI() =
    GetMovieDetailsFromIdUI(
        movieDetailsOverview = overview ?: "",
        movieDetailsTagline = tagline ?: "",
        movieDetailsGenre = genres.joinToString(", ") {it.name.toString()},
        movieDetailsReleaseDate = releaseDate ?: "",
        movieDetailsOriginalTitle = originalTitle ?: "",
        movieDetailsVerticalPoster = posterPath ?: "",
        movieDetailsHorizontalPoster = backdropPath ?: ""
    )

fun CastingForMovieFromTMDB.toMovieDetailsCastUI() = cast.map {
    GetMovieDetailsCastUI(
        movieDetailsCastPoster = it.moviesCastProfilePath ?: "",
        movieDetailsCastName = it.moviesCastName ?: "",
        movieDetailsCastCharacterName = it.moviesCastCharacter ?: ""
    )
}

fun MovieCast.toCastUI() = GetMovieDetailsCastUI(
    movieDetailsCastPoster = moviesCastProfilePath ?: "",
    movieDetailsCastName = moviesCastName ?: "",
    movieDetailsCastCharacterName = moviesCastCharacter ?: ""
)

fun CastingForMovieFromTMDB.toMovieDetailsCrewUI() = crew.map {
    GetMovieDetailsCrewUI(
        movieDetailsCrewPoster = it.moviesCrewProfilePath ?: "",
        movieDetailsCrewJob = it.moviesCrewJob ?: "",
        movieDetailsCrewName = it.moviesCrewName ?: ""
    )
}

fun MovieCrew.toCrewUI() = GetMovieDetailsCrewUI(
    movieDetailsCrewPoster = moviesCrewProfilePath ?: "",
    movieDetailsCrewJob = moviesCrewJob ?: "",
    movieDetailsCrewName = moviesCrewName ?: ""
)

fun CastingForMovieFromTMDB.toMovieDetailsCastListUI() =
    MovieDetailsCastListUI(
        movieCastList = cast.map {
            GetMovieDetailsCastUI(
                movieDetailsCastPoster = it.moviesCastProfilePath ?: "",
                movieDetailsCastName = it.moviesCastName ?: "",
                movieDetailsCastCharacterName = it.moviesCastCharacter ?: ""
            )
        }
    )

fun CastingForMovieFromTMDB.toMovieDetailsCrewListUI() =
    MovieDetailsCrewListUI(
        movieCrewList = crew.map {
            GetMovieDetailsCrewUI(
                movieDetailsCrewPoster = it.moviesCrewProfilePath ?: "",
                movieDetailsCrewJob = it.moviesCrewJob ?: "",
                movieDetailsCrewName = it.moviesCrewName ?: ""
            )
        }
    )
