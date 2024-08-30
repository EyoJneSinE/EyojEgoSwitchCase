package com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerviewBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.adapter.MovieDetailsCrewAdapter
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails

class MovieDetailsCrewRecyclerViewHolder(
    private val binding: MovieDetailsCrewRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovieDetailsCrewRecyclerview(item: MovieDetails.MovieDetailsCrewListUI) {
        binding.movieDetailsCrewRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        init(item)
    }

    fun init(item : MovieDetails.MovieDetailsCrewListUI) {
        binding.movieDetailsCrewRV.adapter = MovieDetailsCrewAdapter().apply {
            submitList(item.movieCrewList)
        }
    }
}