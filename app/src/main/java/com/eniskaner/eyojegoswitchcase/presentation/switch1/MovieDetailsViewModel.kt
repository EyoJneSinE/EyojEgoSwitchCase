package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailsUseCase
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _stateMovieDetails = MutableStateFlow(MovieDetailState())
    val stateMovieDetails : StateFlow<MovieDetailState> = _stateMovieDetails

    private var jobMovieDetails : Job? = null

    fun getMovieDetails(imdbId: Int) {
        jobMovieDetails?.cancel()
        jobMovieDetails = getMovieDetailsUseCase.invoke(movieId = imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieDetails.value = MovieDetailState(movieDetails = it.data)
                }
                is Resource.Error -> {
                    _stateMovieDetails.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieDetails.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}