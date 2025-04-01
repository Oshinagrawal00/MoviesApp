package com.example.movieswatchnow.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movieswatchnow.MoviesApplication
import com.example.movieswatchnow.R
import com.example.movieswatchnow.core.extensions.viewBinding
import com.example.movieswatchnow.databinding.FragmentMovieSearchBinding
import com.example.movieswatchnow.domain.Movie
import com.example.movieswatchnow.presentation.adapter.MovieListAdapter
import com.example.movieswatchnow.presentation.viewmodel.MovieDaoViewModel
import com.example.movieswatchnow.presentation.viewmodel.MovieViewModel
import com.example.movieswatchnow.presentation.viewmodel.MovieViewModelFactory

class MovieSearchFragment : Fragment(R.layout.fragment_movie_search) {

    private val binding by viewBinding(FragmentMovieSearchBinding::bind)


    private val moviesViewModel: MovieViewModel by activityViewModels()
    private var movieListAdapter: MovieListAdapter? = null
    private val moviesDaoViewModel: MovieDaoViewModel by activityViewModels {
        MovieViewModelFactory((activity?.application as MoviesApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initMovieLiveDataObserver()
        Log.d("Launched 3rd fragment", "launched")
        binding.searchBtn.setOnClickListener {
            getMovieSearchList()
        }
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
        moviesViewModel.movieSearchedLiveData.observe(viewLifecycleOwner) {
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

    private fun getMovieSearchList() {
        binding.apply {
            moviesViewModel.getMovieSearch(
                editSearchWord.text.toString(),
                radioAdultContent.checkedRadioButtonId == R.id.radio_pirates,
                languageEditText.text.toString()
            )
        }
    }

    fun addToSavedList(movie: Movie?) {
        if (movie != null) {
            moviesDaoViewModel.insert(movie)
        }
    }
}