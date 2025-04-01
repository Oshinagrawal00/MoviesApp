package com.example.movieswatchnow.domain

data class MovieDetails(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var belongsToCollection: String? = null,
    var budget: Int? = null,
    var genres: ArrayList<Genres> = arrayListOf(),
    var homepage: String? = null,
    var id: Int? = null,
    var imdbId: String? = null,
    var originCountry: ArrayList<String> = arrayListOf(),
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    var productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
)

data class Genres(
    var id: Int? = null,
    var name: String? = null
)

data class ProductionCompanies(
    var id: Int? = null,
    var logoPath: String? = null,
    var name: String? = null,
    var originCountry: String? = null
)

data class ProductionCountries(
    var iso31661: String? = null,
    var name: String? = null
)

data class SpokenLanguages(
    var englishName: String? = null,
    var iso6391: String? = null,
    var name: String? = null
)
