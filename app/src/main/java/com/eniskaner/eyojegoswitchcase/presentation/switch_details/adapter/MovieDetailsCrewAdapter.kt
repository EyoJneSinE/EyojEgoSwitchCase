package com.eniskaner.eyojegoswitchcase.presentation.switch_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder.MovieDetailsCrewViewHolder

class MovieDetailsCrewAdapter: ListAdapter<MovieDetails.GetMovieDetailsCrewUI, MovieDetailsCrewViewHolder>(
    MovieDetailsCrewDiffCallBack()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCrewViewHolder {
        val binding = MovieDetailsCrewRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsCrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailsCrewViewHolder, position: Int) {
        holder.bindMovieDetailsCrew(getItem(position) as MovieDetails.GetMovieDetailsCrewUI)
    }

}