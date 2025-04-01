package com.example.movieswatchnow

import android.app.Application
import com.example.movieswatchnow.core.MovieDatabase
import com.example.movieswatchnow.domain.repo.MovieDaoRepository

class MoviesApplication: Application()  {
    val database by lazy { MovieDatabase.getDatabase(this) }
    val repository by lazy { MovieDaoRepository(database.dao()) }
}