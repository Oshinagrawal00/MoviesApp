package com.example.movieswatchnow.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MovieList(
    var page: Int? = null,
    var results: ArrayList<Movie> = arrayListOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null
)

@Entity
data class Movie(
    var backdropPath: String? = null,
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var mediaType: String? = null,
    var adult: Boolean? = null,
    var originalLanguage: String? = null,
   // var genreIds: List<Int> = emptyList<Int>(),
    var popularity: Double? = null,
    var releaseDate: String? = null,
    var video: Boolean? = null,
    var voteAverage: Int? = null,
    var voteCount: Int? = null
)
