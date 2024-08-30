package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.DiffUtil

class MovieDetailsDiffCallBack: DiffUtil.ItemCallback<MovieDetails>() {
    override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return when {
            (oldItem is MovieDetails.GetMovieDetailsFromId && newItem is MovieDetails.GetMovieDetailsFromId) -> {
                oldItem.movieDetailsGenre == newItem.movieDetailsGenre ||
                        oldItem.movieDetailsOverview == newItem.movieDetailsOverview ||
                        oldItem.movieDetailsTagline == newItem.movieDetailsTagline ||
                        oldItem.movieDetailsHorizontalPoster == newItem.movieDetailsHorizontalPoster ||
                        oldItem.movieDetailsOriginalTitle == newItem.movieDetailsOriginalTitle ||
                        oldItem.movieDetailsReleaseDate == newItem.movieDetailsReleaseDate ||
                        oldItem.movieDetailsVerticalPoster == newItem.movieDetailsVerticalPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCast && newItem is MovieDetails.GetMovieDetailsCast) -> {
                oldItem.movieDetailsCastName == newItem.movieDetailsCastName ||
                        oldItem.movieDetailsCastCharacterName == newItem.movieDetailsCastCharacterName ||
                        oldItem.movieDetailsCastPoster == newItem.movieDetailsCastPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCrew && newItem is MovieDetails.GetMovieDetailsCrew) -> {
                oldItem.movieDetailsCrewName == newItem.movieDetailsCrewName ||
                        oldItem.movieDetailsCrewJob == newItem.movieDetailsCrewJob ||
                        oldItem.movieDetailsCrewPoster == newItem.movieDetailsCrewPoster
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem == newItem
    }
}

class MovieDetailsCastDiffCallBack : DiffUtil.ItemCallback<MovieDetails.GetMovieDetailsCast>() {
    override fun areItemsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCast,
        newItem: MovieDetails.GetMovieDetailsCast
    ): Boolean = oldItem.movieDetailsCastName == newItem.movieDetailsCastName ||
            oldItem.movieDetailsCastCharacterName == newItem.movieDetailsCastCharacterName ||
            oldItem.movieDetailsCastPoster == newItem.movieDetailsCastPoster

    override fun areContentsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCast,
        newItem: MovieDetails.GetMovieDetailsCast
    ): Boolean = oldItem == newItem

}

class MovieDetailsCrewDiffCallBack : DiffUtil.ItemCallback<MovieDetails.GetMovieDetailsCrew>() {
    override fun areItemsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCrew,
        newItem: MovieDetails.GetMovieDetailsCrew
    ): Boolean = oldItem.movieDetailsCrewJob == newItem.movieDetailsCrewJob ||
            oldItem.movieDetailsCrewPoster == newItem.movieDetailsCrewPoster ||
            oldItem.movieDetailsCrewName == newItem.movieDetailsCrewName

    override fun areContentsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCrew,
        newItem: MovieDetails.GetMovieDetailsCrew
    ): Boolean = oldItem == newItem

}