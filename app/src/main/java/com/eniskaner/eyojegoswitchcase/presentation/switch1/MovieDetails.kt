package com.eniskaner.eyojegoswitchcase.presentation.switch1

import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_DETAILS


sealed class MovieDetails : MovieDetailsDisplayItem {

    data class GetMovieDetailsFromIdUI(
        val movieDetailsHorizontalPoster : String,
        val movieDetailsVerticalPoster : String,
        val movieDetailsOriginalTitle : String,
        val movieDetailsTagline : String,
        val movieDetailsGenre : String,
        val movieDetailsReleaseDate : String,
        val movieDetailsOverview : String
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_DETAILS
        }
    }

    data class MovieDetailsCastListUI(
        var movieCastList: List<GetMovieDetailsCastUI>
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CAST_LIST
        }
    }

    data class GetMovieDetailsCastUI(
        val movieDetailsCastPoster : String,
        val movieDetailsCastName : String,
        val movieDetailsCastCharacterName : String
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CAST
        }
    }

    data class MovieDetailsCrewListUI(
        var movieCrewList: List<GetMovieDetailsCrewUI>
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CREW_LIST
        }
    }

    data class GetMovieDetailsCrewUI(
        val movieDetailsCrewPoster : String,
        val movieDetailsCrewName : String,
        val movieDetailsCrewJob : String
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CREW
        }
    }
}