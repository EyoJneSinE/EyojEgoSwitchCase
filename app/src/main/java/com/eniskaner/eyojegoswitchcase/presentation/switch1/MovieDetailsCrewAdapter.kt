package com.eniskaner.eyojegoswitchcase.presentation.switch1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerRowBinding

class MovieDetailsCrewAdapter: ListAdapter<MovieDetails.GetMovieDetailsCrew, MovieDetailsCrewViewHolder>(MovieDetailsCrewDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCrewViewHolder {
        val binding = MovieDetailsCrewRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsCrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailsCrewViewHolder, position: Int) {
        holder.bindMovieDetailsCrew(getItem(position) as MovieDetails.GetMovieDetailsCrew)
    }

}