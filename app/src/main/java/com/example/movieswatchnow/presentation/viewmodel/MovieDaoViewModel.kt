package com.example.movieswatchnow.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieswatchnow.domain.Movie
import com.example.movieswatchnow.domain.repo.MovieDaoRepository
import kotlinx.coroutines.launch

class MovieDaoViewModel(private val repository: MovieDaoRepository) : ViewModel() {

    //val allWords: List<Movie> = repository.movieItems

    fun insert(word: Movie) = viewModelScope.launch {
        repository.insert(word)
    }
}

class MovieViewModelFactory(private val repository: MovieDaoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDaoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDaoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}