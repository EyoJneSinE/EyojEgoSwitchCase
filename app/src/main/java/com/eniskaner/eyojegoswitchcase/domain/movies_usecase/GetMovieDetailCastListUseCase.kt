package com.eniskaner.eyojegoswitchcase.domain.movies_usecase

import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import javax.inject.Inject

class GetMovieDetailCastListUseCase @Inject constructor(
    private val movieDetailsRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = movieDetailsRepository.getMovieDetailCastList(movieId)
}