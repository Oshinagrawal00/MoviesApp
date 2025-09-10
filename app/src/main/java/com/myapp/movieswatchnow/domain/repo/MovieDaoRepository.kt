package com.myapp.movieswatchnow.domain.repo

import androidx.annotation.WorkerThread
import com.myapp.movieswatchnow.core.MovieDao
import com.myapp.movieswatchnow.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDaoRepository(private val movieDao: MovieDao) {


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movieSaved: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.saveMovies(movieSaved)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(movieSaved: Int) {
        withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movieSaved)
        }
    }
}