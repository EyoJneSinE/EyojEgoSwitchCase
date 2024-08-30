package com.eniskaner.eyojegoswitchcase.domain.repo

import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsFromIdUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCastListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCrewListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCastUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCrewUI
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieDetailsFromTMDB(movieId: Int): Flow<Resource<GetMovieDetailsFromIdUI>>

    fun getMovieDetailCastList(movieId: Int): Flow<Resource<MovieDetailsCastListUI>>

    fun getMovieDetailCrewList(movieId: Int): Flow<Resource<MovieDetailsCrewListUI>>

    fun getMovieDetailCast(movieId: Int): Flow<Resource<List<GetMovieDetailsCastUI>>>

    fun getMovieDetailCrew(movieId: Int): Flow<Resource<List<GetMovieDetailsCrewUI>>>

}