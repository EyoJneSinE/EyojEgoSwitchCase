package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerviewBinding

class MovieDetailsCastRecyclerViewHolder(
    private val binding: MovieDetailsCastRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovieDetailsCastRecyclerview(item: MovieDetails.MovieDetailsCastListUI) {
        binding.movieDetailsCastRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        init(item)
    }

    fun init(item : MovieDetails.MovieDetailsCastListUI) {
        binding.movieDetailsCastRV.adapter = MovieDetailsCastAdapter().apply {
            submitList(item.movieCastList)
        }
    }
}