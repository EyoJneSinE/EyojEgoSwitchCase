package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.util.load
import com.eniskaner.eyojegoswitchcase.presentation.util.loadCircleCrop

class MovieDetailsCastViewHolder(private val movieDetailsCastBinding: MovieDetailsCastRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCastBinding.root) {
    fun bindMovieDetailsCast(movieDetailsCastItem: MovieDetails.GetMovieDetailsCastUI) {
        movieDetailsCastBinding.apply {
            movieDetailsCastNameTextView.text = movieDetailsCastItem.movieDetailsCastName
            movieDetailsCastCharacterContentsTextView.text = movieDetailsCastItem.movieDetailsCastCharacterName
            moviesCastMemberPoster.loadCircleCrop(POSTER_URL + movieDetailsCastItem.movieDetailsCastPoster)
        }
    }
}