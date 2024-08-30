package com.eniskaner.eyojegoswitchcase.presentation.switch_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder.MovieDetailsCastViewHolder

class MovieDetailsCastAdapter: ListAdapter<MovieDetails.GetMovieDetailsCastUI, MovieDetailsCastViewHolder>(
    MovieDetailsCastDiffCallBack()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCastViewHolder {
        val binding = MovieDetailsCastRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailsCastViewHolder, position: Int) {
        holder.bindMovieDetailsCast(getItem(position) as MovieDetails.GetMovieDetailsCastUI)
    }
}