package com.eniskaner.eyojegoswitchcase.presentation.switch1

import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW_LIST
import com.eniskaner.eyojegoswitchcase.presentation.switch1.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_DETAILS


sealed class MovieDetails : MovieDetailsDisplayItem {

    data class GetMovieDetailsFromId(
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

    data class MovieDetailsCastList(
        var movieCastList: List<GetMovieDetailsCast> = emptyList()
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CAST_LIST
        }
    }

    data class GetMovieDetailsCast(
        val movieDetailsCastPoster : String,
        val movieDetailsCastName : String,
        val movieDetailsCastCharacterName : String
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CAST
        }
    }

    data class MovieDetailsCrewList(
        var movieCrewList: List<GetMovieDetailsCrew> = emptyList()
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CREW_LIST
        }
    }

    data class GetMovieDetailsCrew(
        val movieDetailsCrewPoster : String,
        val movieDetailsCrewName : String,
        val movieDetailsCrewJob : String
    ) : MovieDetails(), MovieDetailsDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIE_CREW
        }
    }
}