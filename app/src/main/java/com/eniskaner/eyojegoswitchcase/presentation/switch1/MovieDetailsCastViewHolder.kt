package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.util.load

class MovieDetailsCastViewHolder(private val movieDetailsCastBinding: MovieDetailsCastRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCastBinding.root) {
    fun bindMovieDetailsCast(movieDetailsCastItem: MovieDetails.GetMovieDetailsCast) {
        movieDetailsCastBinding.apply {
            movieDetailsCastNameTextView.text = movieDetailsCastItem.movieDetailsCastName
            movieDetailsCastCharacterContentsTextView.text = movieDetailsCastItem.movieDetailsCastCharacterName
            moviesCastMemberPoster.load(POSTER_URL + movieDetailsCastItem.movieDetailsCastPoster)
        }
    }
}