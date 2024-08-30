package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.BACKDROP_URL
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.util.load

class MovieDetailsViewHolder(private val movieDetailsBinding: MovieDetailsRecyclerRowBinding
) :RecyclerView.ViewHolder(movieDetailsBinding.root) {
    fun bindMovieDetails(movieDetailsItem: MovieDetails.GetMovieDetailsFromId) {
        movieDetailsBinding.apply {
            movieDetailsOverviewContentsTextView.text = movieDetailsItem.movieDetailsOverview
            movieDetailsGenreTypeTextView.text = movieDetailsItem.movieDetailsGenre
            movieDetailsReleaseDateTextView.text = movieDetailsItem.movieDetailsReleaseDate
            movieDetailsTaglineContentsTypeTextView.text = movieDetailsItem.movieDetailsTagline
            moviesOriginalTitleTextView.text = movieDetailsItem.movieDetailsOriginalTitle
            moviesHorizontalPoster.load(BACKDROP_URL + movieDetailsItem.movieDetailsHorizontalPoster)
            moviesVerticalPoster.load(POSTER_URL + movieDetailsItem.movieDetailsVerticalPoster)
        }
    }
}