package com.eniskaner.eyojegoswitchcase.data.remote

import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.THE_MOVIEDB_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetailFromTMDB(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GetMovieDetailsFromId

    @GET("/3/movie/{movieId}/credits")
    suspend fun getCastFromTMDB(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): CastingForMovieFromTMDB

    @GET("/3/genre/movie/list")
    suspend fun genreMovieFromTMDB(
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GenresFromTMDB
}