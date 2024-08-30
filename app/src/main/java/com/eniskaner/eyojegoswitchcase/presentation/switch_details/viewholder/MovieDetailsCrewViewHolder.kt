package com.eniskaner.eyojegoswitchcase.presentation.switch_details.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.eyojegoswitchcase.data.remote.util.Constants.POSTER_URL
import com.eniskaner.eyojegoswitchcase.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.eyojegoswitchcase.presentation.switch_details.model.MovieDetails
import com.eniskaner.eyojegoswitchcase.presentation.util.loadCircleCrop

class MovieDetailsCrewViewHolder(private val movieDetailsCrewBinding: MovieDetailsCrewRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCrewBinding.root) {
    fun bindMovieDetailsCrew(movieDetailsCastItem: MovieDetails.GetMovieDetailsCrewUI) {
        movieDetailsCrewBinding.apply {
            movieDetailsCrewNameTextView.text = movieDetailsCastItem.movieDetailsCrewName
            movieDetailsCrewJobContentsTextView.text = movieDetailsCastItem.movieDetailsCrewJob
            moviesCrewMemberPoster.loadCircleCrop(POSTER_URL + movieDetailsCastItem.movieDetailsCrewPoster)
        }
    }
}