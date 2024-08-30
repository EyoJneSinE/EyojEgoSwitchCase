package com.eniskaner.eyojegoswitchcase.presentation.switch1

import androidx.recyclerview.widget.DiffUtil

class MovieDetailsDiffCallBack: DiffUtil.ItemCallback<MovieDetails>() {
    override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return when {
            (oldItem is MovieDetails.GetMovieDetailsFromIdUI && newItem is MovieDetails.GetMovieDetailsFromIdUI) -> {
                oldItem.movieDetailsGenre == newItem.movieDetailsGenre ||
                        oldItem.movieDetailsOverview == newItem.movieDetailsOverview ||
                        oldItem.movieDetailsTagline == newItem.movieDetailsTagline ||
                        oldItem.movieDetailsHorizontalPoster == newItem.movieDetailsHorizontalPoster ||
                        oldItem.movieDetailsOriginalTitle == newItem.movieDetailsOriginalTitle ||
                        oldItem.movieDetailsReleaseDate == newItem.movieDetailsReleaseDate ||
                        oldItem.movieDetailsVerticalPoster == newItem.movieDetailsVerticalPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCastUI && newItem is MovieDetails.GetMovieDetailsCastUI) -> {
                oldItem.movieDetailsCastName == newItem.movieDetailsCastName ||
                        oldItem.movieDetailsCastCharacterName == newItem.movieDetailsCastCharacterName ||
                        oldItem.movieDetailsCastPoster == newItem.movieDetailsCastPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCrewUI && newItem is MovieDetails.GetMovieDetailsCrewUI) -> {
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

class MovieDetailsCastDiffCallBack : DiffUtil.ItemCallback<MovieDetails.GetMovieDetailsCastUI>() {
    override fun areItemsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCastUI,
        newItem: MovieDetails.GetMovieDetailsCastUI
    ): Boolean = oldItem.movieDetailsCastName == newItem.movieDetailsCastName ||
            oldItem.movieDetailsCastCharacterName == newItem.movieDetailsCastCharacterName ||
            oldItem.movieDetailsCastPoster == newItem.movieDetailsCastPoster

    override fun areContentsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCastUI,
        newItem: MovieDetails.GetMovieDetailsCastUI
    ): Boolean = oldItem == newItem

}

class MovieDetailsCrewDiffCallBack : DiffUtil.ItemCallback<MovieDetails.GetMovieDetailsCrewUI>() {
    override fun areItemsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCrewUI,
        newItem: MovieDetails.GetMovieDetailsCrewUI
    ): Boolean = oldItem.movieDetailsCrewJob == newItem.movieDetailsCrewJob ||
            oldItem.movieDetailsCrewPoster == newItem.movieDetailsCrewPoster ||
            oldItem.movieDetailsCrewName == newItem.movieDetailsCrewName

    override fun areContentsTheSame(
        oldItem: MovieDetails.GetMovieDetailsCrewUI,
        newItem: MovieDetails.GetMovieDetailsCrewUI
    ): Boolean = oldItem == newItem

}