package com.example.movieswatchnow.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieswatchnow.data.datasource.MoviesDataSource
import com.example.movieswatchnow.domain.MovieDetails
import com.example.movieswatchnow.domain.MovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieData : MoviesDataSource): BaseViewModel() {

    val movieListLiveData = MutableLiveData<MovieList>()
    val movieSearchedLiveData = MutableLiveData<MovieList>()
    val movieItemId = MutableLiveData<String>()
    val moviePicture = MutableLiveData<String>()
    val movieDetailsLiveData = MutableLiveData<MovieDetails>()

    fun getMovieList() {
        viewModelScope.launch {
           movieData.getTrendingMovies().apply {
               movieListLiveData.value = this
           }
        }
    }

    fun setMovieItemId(movieId: String, moviePic: String) {
        movieItemId.value = movieId
        moviePicture.value = moviePic
    }

     fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            movieData.getMovieDetails(movieId).apply {
                val x = this.title
                Log.d(x, "pqr")
                movieDetailsLiveData.value = this
            }
        }
    }

    fun getMovieSearch(searchRequest: String, includeAdult: Boolean, language: String) {
        viewModelScope.launch {
            movieData.searchMovieResult(searchRequest, includeAdult, language).apply {
                movieSearchedLiveData.value = this
            }
        }
    }

}