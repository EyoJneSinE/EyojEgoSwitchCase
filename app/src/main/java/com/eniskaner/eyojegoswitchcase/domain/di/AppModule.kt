package com.eniskaner.eyojegoswitchcase.domain.di

import com.eniskaner.eyojegoswitchcase.data.remote.MovieAPI
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.THE_MOVIEDB_URL
import com.eniskaner.eyojegoswitchcase.data.repo.MovieRepositoryImpl
import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi() : MovieAPI {
        return Retrofit.Builder()
            .baseUrl(THE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieAPI: MovieAPI) : MovieRepository {
        return MovieRepositoryImpl(movieApi = movieAPI)
    }
}