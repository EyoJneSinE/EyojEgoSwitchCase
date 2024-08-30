package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.util.load

class MovieDetailsCrewViewHolder(private val movieDetailsCrewBinding: MovieDetailsCrewRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCrewBinding.root) {
    fun bindMovieDetailsCrew(movieDetailsCastItem: MovieDetails.GetMovieDetailsCrew) {
        movieDetailsCrewBinding.apply {
            movieDetailsCrewNameTextView.text = movieDetailsCastItem.movieDetailsCrewName
            movieDetailsCrewJobContentsTextView.text = movieDetailsCastItem.movieDetailsCrewJob
            moviesCrewMemberPoster.load(POSTER_URL + movieDetailsCastItem.movieDetailsCrewPoster)
        }
    }
}