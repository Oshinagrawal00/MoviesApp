package com.myapp.movieswatchnow.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myapp.movieswatchnow.domain.Movie

@Dao
interface MovieDao {

    @Insert
    fun saveMovies(movie: Movie)

    @Query("DELETE FROM Movie WHERE id = :movie")
    fun deleteMovie(movie: Int)

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    fun getSavedMovieList(): List<Movie>

}