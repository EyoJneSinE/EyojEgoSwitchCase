package com.eniskaner.eyojegoswitchcase.presentation.switch5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.eyojegoswitchcase.databinding.FragmentSwitch5Binding
import com.eniskaner.eyojegoswitchcase.presentation.mainswitches.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.adapter.MovieDetailsAdapter
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.state.MovieDetailState
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewmodel.MovieDetailsViewModel
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewmodel.MoviesCastViewModel
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewmodel.MoviesCrewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Switch5Fragment : Fragment() {

    private var _binding: FragmentSwitch5Binding? = null
    private val binding get() = _binding
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val movieDetailsCastViewModel: MoviesCastViewModel by viewModels()
    private val movieDetailsCrewViewModel: MoviesCrewViewModel by viewModels()
    private val movieDetailsAdapter: MovieDetailsAdapter by lazy {
        MovieDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSwitch5Binding.inflate(inflater, container, false)
        binding?.recyclerViewYesMan?.layoutManager = LinearLayoutManager(requireContext())
        launchAndRepeatWithViewLifecycle {
            launch {
                val movieId = 10201
                movieDetailsViewModel.getMovieDetails(movieId)
                movieDetailsCastViewModel.getMovieCast(movieId)
                movieDetailsCrewViewModel.getMovieCrew(movieId)
                movieDetailsCastViewModel.getMovieCastList(movieId)
                movieDetailsCrewViewModel.getMovieCrewList(movieId)
            }
        }
        setTrendingRecyclerView()
        getMovieDetailsData()
        return binding?.root
    }

    private fun getMovieDetailsData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                combine(
                    movieDetailsViewModel.stateMovieDetails,
                    movieDetailsCastViewModel.stateMovieCast,
                    movieDetailsCrewViewModel.stateMovieCrew
                ) {movieDetailsState, movieDetailsCastState, movieDetailsCrewState ->
                    movieDetailData(movieDetailsState, movieDetailsCastState, movieDetailsCrewState)
                }.collectLatest { data ->
                    movieDetailsAdapter.submitList(data)
                }
            }
        }
    }

    private fun movieDetailData (
        movieDetailsState: MovieDetailState,
        movieDetailsCastState: MovieDetailState,
        movieDetailsCrewState: MovieDetailState
    ): List<MovieDetails> {
        val movieDetailsList = mutableListOf<MovieDetails>()
        val movieDetails = mutableListOf<MovieDetails.GetMovieDetailsFromIdUI>()
        val movieDetailsCastList = mutableListOf<MovieDetails.MovieDetailsCastListUI>()
        val movieDetailsCrewList = mutableListOf<MovieDetails.MovieDetailsCrewListUI>()
        movieDetailsState.movieDetails?.let { movieDetails.add(it) }
        movieDetailsList.addAll(movieDetails)
        val castList = movieDetailsCastState.movieCast?.let { MovieDetails.MovieDetailsCastListUI(movieCastList = it) }
        castList?.let {
            movieDetailsCastList.add(castList)
        }
        movieDetailsList.addAll(movieDetailsCastList)
        val crewList = movieDetailsCrewState.movieCrew?.let { MovieDetails.MovieDetailsCrewListUI(movieCrewList = it) }
        crewList?.let {
            movieDetailsCrewList.add(crewList)
        }
        movieDetailsList.addAll(movieDetailsCrewList)
        return movieDetailsList
    }

    private fun setTrendingRecyclerView() {
        binding?.recyclerViewYesMan?.run {
            hasFixedSize()
            adapter = movieDetailsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}