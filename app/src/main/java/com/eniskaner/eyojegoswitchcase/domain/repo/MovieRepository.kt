package com.eniskaner.eyojegoswitchcase.domain.repo

import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB

interface MovieRepository {

    suspend fun getMovieDetailsFromTMDB(movieId: Int): GetMovieDetailsFromId

    suspend fun getCastFromTMDB(movieId:Int): CastingForMovieFromTMDB

    suspend fun genreMovieFromTMDB(): GenresFromTMDB
}