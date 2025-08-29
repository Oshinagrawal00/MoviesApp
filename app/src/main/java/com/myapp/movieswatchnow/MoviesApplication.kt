package com.myapp.movieswatchnow

import android.app.Application
import com.myapp.movieswatchnow.core.MovieDatabase
import com.myapp.movieswatchnow.domain.repo.MovieDaoRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication: Application()  {
    val database by lazy { MovieDatabase.getDatabase(this) }
    val repository by lazy { MovieDaoRepository(database.dao()) }
}