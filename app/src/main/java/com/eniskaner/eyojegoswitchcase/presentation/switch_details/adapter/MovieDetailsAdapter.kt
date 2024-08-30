package com.eniskaner.eyojegoswitchcase.presentation.switch_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerviewBinding
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerviewBinding
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_DETAILS
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder.MovieDetailsCastRecyclerViewHolder
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder.MovieDetailsCrewRecyclerViewHolder
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder.MovieDetailsViewHolder

class MovieDetailsAdapter() : ListAdapter<MovieDetails, RecyclerView.ViewHolder>(
    MovieDetailsDiffCallBack()
)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {

            TYPE_MOVIE_DETAILS -> {
                val movieDetailsBinding =
                    MovieDetailsRecyclerRowBinding.inflate(layoutInflater, parent, false)
                MovieDetailsViewHolder(movieDetailsBinding)
            }

            TYPE_MOVIE_CAST -> {
                val movieDetailsCastBinding =
                    MovieDetailsCastRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCastRecyclerViewHolder(movieDetailsCastBinding)
            }

            TYPE_MOVIE_CAST_LIST -> {
                val movieDetailsCastBinding =
                    MovieDetailsCastRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCastRecyclerViewHolder(movieDetailsCastBinding)
            }

            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewBinding =
                    MovieDetailsCrewRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCrewRecyclerViewHolder(movieDetailsCrewBinding)
            }

            TYPE_MOVIE_CREW_LIST -> {
                val movieDetailsCrewBinding =
                    MovieDetailsCrewRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCrewRecyclerViewHolder(movieDetailsCrewBinding)
            }

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.type()) {
            TYPE_MOVIE_DETAILS -> {
                val movieDetailsHolder = holder as MovieDetailsViewHolder
                movieDetailsHolder.bindMovieDetails(item as MovieDetails.GetMovieDetailsFromIdUI)
            }

            TYPE_MOVIE_CAST_LIST -> {
                val movieDetailsCastHolder = holder as MovieDetailsCastRecyclerViewHolder
                movieDetailsCastHolder.bindMovieDetailsCastRecyclerview(item as MovieDetails.MovieDetailsCastListUI)
            }

            TYPE_MOVIE_CREW_LIST -> {
                val movieDetailsCrewHolder = holder as MovieDetailsCrewRecyclerViewHolder
                movieDetailsCrewHolder.bindMovieDetailsCrewRecyclerview(item as MovieDetails.MovieDetailsCrewListUI)
            }

            TYPE_MOVIE_CAST -> {
                val movieDetailsCastHolder = holder as MovieDetailsCastRecyclerViewHolder
                movieDetailsCastHolder.bindMovieDetailsCastRecyclerview(item as MovieDetails.MovieDetailsCastListUI)
            }

            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewHolder = holder as MovieDetailsCrewRecyclerViewHolder
                movieDetailsCrewHolder.bindMovieDetailsCrewRecyclerview(item as MovieDetails.MovieDetailsCrewListUI)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type()
}