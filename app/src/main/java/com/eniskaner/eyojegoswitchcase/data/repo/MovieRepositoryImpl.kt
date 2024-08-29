package com.eniskaner.eyojegoswitchcase.data.repo

import com.eniskaner.eyojegoswitchcase.data.remote.MovieAPI
import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB
import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl@Inject constructor(private val movieApi: MovieAPI): MovieRepository {
    override suspend fun getMovieDetailsFromTMDB(movieId: Int): GetMovieDetailsFromId {
        return movieApi.getMovieDetailFromTMDB(movieId)
    }

    override suspend fun getCastFromTMDB(movieId: Int): CastingForMovieFromTMDB {
        return movieApi.getCastFromTMDB(movieId)
    }

    override suspend fun genreMovieFromTMDB(): GenresFromTMDB {
        return movieApi.genreMovieFromTMDB()
    }
}