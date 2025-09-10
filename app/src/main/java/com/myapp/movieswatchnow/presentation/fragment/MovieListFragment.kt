package com.myapp.movieswatchnow.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.myapp.movieswatchnow.R
import com.myapp.movieswatchnow.MoviesApplication
import com.myapp.movieswatchnow.databinding.FragmentMoviesListBinding
import com.myapp.movieswatchnow.core.extensions.viewBinding
import com.myapp.movieswatchnow.domain.Movie
import com.myapp.movieswatchnow.presentation.adapter.MovieListAdapter
import com.myapp.movieswatchnow.presentation.viewmodel.MovieDaoViewModel
import com.myapp.movieswatchnow.presentation.viewmodel.MovieViewModel
import com.myapp.movieswatchnow.presentation.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movies_list) {

    private val binding by viewBinding(FragmentMoviesListBinding::bind)


    private val moviesViewModel: MovieViewModel by activityViewModels()
    private var movieListAdapter: MovieListAdapter? = null
    private val moviesDaoViewModel: MovieDaoViewModel by activityViewModels {
        MovieViewModelFactory((activity?.application as MoviesApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieList()
        initAdapter()
        initMovieLiveDataObserver()
        Log.d("Launched 1st fragment", "launched")
    }

    private fun initAdapter() {
        movieListAdapter = MovieListAdapter()
        movieListAdapter?.apply {
            onShipmentCardClickListener = {
                launchMovieDetail(it)
            }
            onButtonClickListner = {
                addToSavedList(it)
            }
        }
        binding.movieList.adapter = movieListAdapter
    }

    private fun initMovieLiveDataObserver() {
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner) {
            movieListAdapter?.setMovieList(it.results)
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

    private fun getMovieList() {
        moviesViewModel.getMovieList()
    }

    private fun addToSavedList(movie: Movie?) {
        if (movie != null) {
            moviesDaoViewModel.insert(movie)
        }

    }
}