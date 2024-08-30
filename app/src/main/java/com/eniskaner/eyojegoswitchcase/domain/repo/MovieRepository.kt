package com.eniskaner.eyojegoswitchcase.domain.repo

import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails.GetMovieDetailsFromIdUI
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails.MovieDetailsCastListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails.MovieDetailsCrewListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails.GetMovieDetailsCastUI
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails.GetMovieDetailsCrewUI
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieDetailsFromTMDB(movieId: Int): Flow<Resource<GetMovieDetailsFromIdUI>>

    fun getMovieDetailCastList(movieId: Int): Flow<Resource<MovieDetailsCastListUI>>

    fun getMovieDetailCrewList(movieId: Int): Flow<Resource<MovieDetailsCrewListUI>>

    fun getMovieDetailCast(movieId: Int): Flow<Resource<List<GetMovieDetailsCastUI>>>

    fun getMovieDetailCrew(movieId: Int): Flow<Resource<List<GetMovieDetailsCrewUI>>>

}