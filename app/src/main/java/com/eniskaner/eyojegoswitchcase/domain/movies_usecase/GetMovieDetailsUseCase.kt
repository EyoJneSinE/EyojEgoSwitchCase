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
    operator fun invoke(movieId: Int) = movieDetailsRepository.getMovieDetailsFromTMDB(movieId)
}