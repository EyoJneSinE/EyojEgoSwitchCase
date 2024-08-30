package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerviewBinding

class MovieDetailsCrewRecyclerViewHolder(
    private val binding: MovieDetailsCrewRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovieDetailsCrewRecyclerview(item: MovieDetails.MovieDetailsCrewList) {
        binding.movieDetailsCrewRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        init(item)
    }

    fun init(item : MovieDetails.MovieDetailsCrewList) {
        binding.movieDetailsCrewRV.adapter = MovieDetailsCrewAdapter().apply {
            submitList(item.movieCrewList)
        }
    }
}