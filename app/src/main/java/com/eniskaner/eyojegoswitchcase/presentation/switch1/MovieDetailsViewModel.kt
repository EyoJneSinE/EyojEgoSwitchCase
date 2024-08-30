package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.MOVIE_ID
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailsUseCase
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedMovieDetailsStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stateMovieDetails = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieDetails : StateFlow<MovieDetailState> = _stateMovieDetails

    private var jobMovieDetails : Job? = null

    init {
        savedMovieDetailsStateHandle.get<String>(MOVIE_ID.toString())?.let {
            getMovieDetails(it.toInt())
        }
    }

    fun getMovieDetails(imdbId: Int) {
        jobMovieDetails?.cancel()
        jobMovieDetails = getMovieDetailsUseCase.executeGetMovieDetailsFromTMDB(imdbId).onEach {
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