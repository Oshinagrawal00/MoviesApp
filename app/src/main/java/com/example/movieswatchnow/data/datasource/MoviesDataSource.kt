package com.example.movieswatchnow.data.datasource

import com.example.movieswatchnow.data.service.APIInstance
import com.example.movieswatchnow.data.service.MoviesService
import com.example.movieswatchnow.domain.MovieDetails
import com.example.movieswatchnow.domain.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataSource {

    private val moviesService: MoviesService = APIInstance.createService(MoviesService::class.java)

    suspend fun getTrendingMovies(): MovieList =
        withContext(Dispatchers.IO) {
            moviesService.getMoviesList(
                url = "https://api.themoviedb.org/3/trending/movie/day"
            )
        }


    suspend fun getMovieDetails(movieId: String): MovieDetails =
        withContext(Dispatchers.IO) {
            moviesService.getMoviesDetails(url = "https://api.themoviedb.org/3/movie/$movieId")
        }

    suspend fun searchMovieResult(searchRequest: String, includeAdult: Boolean, language: String) =
        withContext(Dispatchers.IO) {
            moviesService.getMoviesSearch(url = "https://api.themoviedb.org/3/search/movie?query=${searchRequest}&include_adult=${includeAdult}&language=${language}&page=1")
        }
}