package com.eniskaner.eyojegoswitchcase.data.repo

import com.eniskaner.eyojegoswitchcase.data.remote.MovieAPI
import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.data.remote.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojegoswitchcase.data.remote.movies_genre.GenresFromTMDB
import com.eniskaner.eyojegoswitchcase.domain.mapper.toCastUI
import com.eniskaner.eyojegoswitchcase.domain.mapper.toMovieDetailsCastListUI
import com.eniskaner.eyojegoswitchcase.domain.mapper.toMovieDetailsCastUI
import com.eniskaner.eyojegoswitchcase.domain.mapper.toMovieDetailsCrewListUI
import com.eniskaner.eyojegoswitchcase.domain.mapper.toMovieDetailsCrewUI
import com.eniskaner.eyojegoswitchcase.domain.mapper.toMovieDetailsUI
import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsFromIdUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCastListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.MovieDetailsCrewListUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCastUI
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails.GetMovieDetailsCrewUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieAPI): MovieRepository {
    override fun getMovieDetailsFromTMDB(movieId: Int): Flow<Resource<GetMovieDetailsFromIdUI>> = flow {
        emit(Resource.Loading())
        try {
            val response = movieApi.getMovieDetailFromTMDB(movieId).toMovieDetailsUI()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

    override fun getMovieDetailCastList(movieId: Int): Flow<Resource<MovieDetailsCastListUI>> = flow {
        emit(Resource.Loading())
        try {
            val response = movieApi.getCastFromTMDB(movieId).toMovieDetailsCastListUI()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

    override fun getMovieDetailCrewList(movieId: Int): Flow<Resource<MovieDetailsCrewListUI>> = flow {
        emit(Resource.Loading())
        try {
            val response = movieApi.getCastFromTMDB(movieId).toMovieDetailsCrewListUI()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

    override fun getMovieDetailCast(movieId: Int): Flow<Resource<List<GetMovieDetailsCastUI>>> = flow {
        emit(Resource.Loading())
        try {
            val response = movieApi.getCastFromTMDB(movieId).toMovieDetailsCastUI()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

    override fun getMovieDetailCrew(movieId: Int): Flow<Resource<List<GetMovieDetailsCrewUI>>> = flow {
        emit(Resource.Loading())
        try {
            val response = movieApi.getCastFromTMDB(movieId).toMovieDetailsCrewUI()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

}