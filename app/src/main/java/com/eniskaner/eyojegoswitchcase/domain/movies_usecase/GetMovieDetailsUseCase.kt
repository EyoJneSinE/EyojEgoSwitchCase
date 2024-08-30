package com.eniskaner.eyojegoswitchcase.domain.movies_usecase

import com.eniskaner.eyojegoswitchcase.data.remote.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieRepository
) {
    fun executeGetMovieDetailsFromTMDB(movieId: Int) : Flow<Resource<GetMovieDetailsFromId>> = flow {
        try {
            emit(Resource.Loading())
            val getMovieDetailsFromTMDB = movieDetailsRepository.getMovieDetailsFromTMDB(movieId)
            if (getMovieDetailsFromTMDB.genres.isNotEmpty()) {
                emit(Resource.Success(getMovieDetailsFromTMDB))
            } else {
                emit(Resource.Error(message = "Movie Details Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}