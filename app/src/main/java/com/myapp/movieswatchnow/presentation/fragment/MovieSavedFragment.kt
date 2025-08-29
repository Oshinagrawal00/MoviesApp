package com.myapp.movieswatchnow.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.room.Room
import com.myapp.movieswatchnow.databinding.FragmentMoviesListBinding
import com.myapp.movieswatchnow.R
import com.myapp.movieswatchnow.MoviesApplication
import com.myapp.movieswatchnow.core.MovieDatabase
import com.myapp.movieswatchnow.core.extensions.viewBinding
import com.myapp.movieswatchnow.domain.Movie
import com.myapp.movieswatchnow.presentation.adapter.SavedMoviesAdapter
import com.myapp.movieswatchnow.presentation.viewmodel.MovieDaoViewModel
import com.myapp.movieswatchnow.presentation.viewmodel.MovieViewModel
import com.myapp.movieswatchnow.presentation.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Timer
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MovieSavedFragment : Fragment(R.layout.fragment_movies_list) {

    private val binding by viewBinding(FragmentMoviesListBinding::bind)


    private val moviesViewModel: MovieViewModel by activityViewModels()
    private var movieListAdapter: SavedMoviesAdapter? = null
    private val moviesDaoViewModel: MovieDaoViewModel by activityViewModels {
        MovieViewModelFactory((activity?.application as MoviesApplication).repository)
    }
    lateinit var movieDao: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDao = Room.databaseBuilder(
            requireContext(),
            MovieDatabase::class.java, "movie_database"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initMovieLiveDataObserver()
        binding.screenHeader.text = "My Favourites"
    }

    fun initAdapter() {
        movieListAdapter = SavedMoviesAdapter()
        movieListAdapter?.apply {
            onShipmentCardClickListener = {
                launchMovieDetail(it)
            }
            onButtonClickListner = {
                removeFromSavedList(it)
            }
        }
        binding.movieList.adapter = movieListAdapter
    }

    private fun initMovieLiveDataObserver() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieDao.dao().getSavedMovieList()
            Log.d("$result", "dfg")
            withContext(Dispatchers.Main) {
                movieListAdapter?.setMovieList(
                    result
                )
            }
        }
    }

    private fun launchMovieDetail(movie: Movie?) {
        moviesViewModel.setMovieItemId(movie?.id.toString(), movie?.poster_path.toString())

        val secondFragment = MovieDetailsFragment()
        // Use FragmentManager to replace the current fragment with SecondFragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, secondFragment)
            .addToBackStack(null) // Optional: Add to back stack for navigation back
            .commit()
    }

    private fun removeFromSavedList(movie: Movie?) {
        if (movie != null) {
            movie.id?.let { moviesDaoViewModel.removeMovie(it) }
        }

        Timer().schedule(2000) {
            initMovieLiveDataObserver()
        }
    }
}