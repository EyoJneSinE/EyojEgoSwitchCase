package com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailCrewListUseCase
import com.eniskaner.eyojegoswitchcase.domain.movies_usecase.GetMovieDetailCrewUseCase
import com.eniskaner.eyojegoswitchcase.domain.util.Resource
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesCrewViewModel @Inject constructor(
    private val getMovieDetailsCrewUseCase: GetMovieDetailCrewUseCase,
    private val getMovieDetailCrewListUseCase: GetMovieDetailCrewListUseCase
): ViewModel() {

    private val _stateMovieCrew = MutableStateFlow(MovieDetailState())
    val stateMovieCrew : StateFlow<MovieDetailState> = _stateMovieCrew

    private var jobMovieCrews : Job? = null
    private var jobMovieCrewList : Job? = null

    fun getMovieCrewList(imdbId: Int) {
        jobMovieCrewList?.cancel()
        jobMovieCrewList = getMovieDetailCrewListUseCase.invoke(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCrew.value = MovieDetailState(movieCrew = it.data?.movieCrewList ?: emptyList())
                }
                is Resource.Error -> {
                    _stateMovieCrew.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCrew.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieCrew(imdbId: Int) {
        jobMovieCrews?.cancel()
        jobMovieCrews = getMovieDetailsCrewUseCase.invoke(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCrew.value = MovieDetailState(movieCrew = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateMovieCrew.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCrew.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}