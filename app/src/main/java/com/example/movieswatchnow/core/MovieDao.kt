package com.example.movieswatchnow.core

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieswatchnow.domain.Movie

@Dao
interface MovieDao {

    @Insert
    fun saveMovies(movie: Movie)

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    fun getSavedMovieList(): List<Movie>

}