package com.eniskaner.eyojegoswitchcase.domain.movies_usecase

import com.eniskaner.eyojegoswitchcase.domain.repo.MovieRepository
import javax.inject.Inject

class GetMovieDetailCrewListUseCase @Inject constructor(
    private val movieDetailsRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = movieDetailsRepository.getMovieDetailCrewList(movieId)
}