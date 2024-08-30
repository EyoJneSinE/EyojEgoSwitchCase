package com.eniskaner.eyojegoswitchcase.presentation.switch1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerRowBinding

class MovieDetailsCastAdapter: ListAdapter<MovieDetails.GetMovieDetailsCast, MovieDetailsCastViewHolder>(MovieDetailsCastDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCastViewHolder {
        val binding = MovieDetailsCastRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailsCastViewHolder, position: Int) {
        holder.bindMovieDetailsCast(getItem(position) as MovieDetails.GetMovieDetailsCast)
    }
}