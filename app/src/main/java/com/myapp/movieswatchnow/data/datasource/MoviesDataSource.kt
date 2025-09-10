package com.myapp.movieswatchnow.data.datasource

import com.myapp.movieswatchnow.data.service.APIInstance
import com.myapp.movieswatchnow.data.service.MoviesService
import com.myapp.movieswatchnow.domain.MovieDetails
import com.myapp.movieswatchnow.domain.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesDataSource @Inject constructor() {

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