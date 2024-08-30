package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailCastListUseCase
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailCastUseCase
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesCastViewModel @Inject constructor(
    private val getMovieDetailsCastUseCase: GetMovieDetailCastUseCase,
    private val getMovieDetailCastListUseCase: GetMovieDetailCastListUseCase
): ViewModel() {

    private val _stateMovieCast = MutableStateFlow(MovieDetailState())
    val stateMovieCast : StateFlow<MovieDetailState> = _stateMovieCast

    private var jobMovieCasts : Job? = null
    private var jobMovieCastList : Job? = null

    fun getMovieCastList(imdbId: Int) {
        jobMovieCastList?.cancel()
        jobMovieCastList = getMovieDetailCastListUseCase.invoke(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCast.value = MovieDetailState(movieCast = it.data?.movieCastList ?: emptyList())
                }
                is Resource.Error -> {
                    _stateMovieCast.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCast.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieCast(imdbId: Int) {
        jobMovieCasts?.cancel()
        jobMovieCasts = getMovieDetailsCastUseCase.invoke(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCast.value = MovieDetailState(movieCast = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateMovieCast.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCast.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}