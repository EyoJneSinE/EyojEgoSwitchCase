package com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.BACKDROP_URL
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.util.loadCenterCrop

class MovieDetailsViewHolder(private val movieDetailsBinding: MovieDetailsRecyclerRowBinding
) :RecyclerView.ViewHolder(movieDetailsBinding.root) {
    fun bindMovieDetails(movieDetailsItem: MovieDetails.GetMovieDetailsFromIdUI) {
        movieDetailsBinding.apply {
            movieDetailsOverviewContentsTextView.text = movieDetailsItem.movieDetailsOverview
            movieDetailsGenreTypeTextView.text = movieDetailsItem.movieDetailsGenre
            movieDetailsReleaseDateTextView.text = movieDetailsItem.movieDetailsReleaseDate
            movieDetailsTaglineContentsTypeTextView.text = movieDetailsItem.movieDetailsTagline
            moviesOriginalTitleTextView.text = movieDetailsItem.movieDetailsOriginalTitle
            moviesHorizontalPoster.loadCenterCrop(BACKDROP_URL + movieDetailsItem.movieDetailsHorizontalPoster)
            moviesVerticalPoster.loadCenterCrop(POSTER_URL + movieDetailsItem.movieDetailsVerticalPoster)
        }
    }
}