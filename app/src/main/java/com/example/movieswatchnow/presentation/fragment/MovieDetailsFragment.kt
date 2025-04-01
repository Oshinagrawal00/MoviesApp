package com.example.movieswatchnow.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieswatchnow.R
import com.example.movieswatchnow.core.extensions.viewBinding
import com.example.movieswatchnow.databinding.FragmentMovieDetailBinding
import com.example.movieswatchnow.domain.MovieDetails
import com.example.movieswatchnow.presentation.utils.MovieHelperUtil.getGenresList
import com.example.movieswatchnow.presentation.utils.MovieHelperUtil.getOriginCountry
import com.example.movieswatchnow.presentation.utils.MovieHelperUtil.getSpokenLanguages
import com.example.movieswatchnow.presentation.viewmodel.MovieViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_detail) {

    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    private val moviesViewModel: MovieViewModel by activityViewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetails()

        initMovieLiveDataObserver()
        Log.d("Launched 2nd fragment", "launched")
    }

    fun initMovieLiveDataObserver() {
        moviesViewModel.movieDetailsLiveData.observe(viewLifecycleOwner) {
            initMovieDetails(it)
        }
    }

    private fun initMovieDetails(movieDetails: MovieDetails) {
        binding.apply {
            imageViewMoviePoster.apply {

                val imageUrl =
                    "https://image.tmdb.org/t/p/w500/${moviesViewModel.moviePicture.value}" // Replace with your image URL

                // Use Glide to load the image from the URL
                Glide.with(this)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_movie_image) // Placeholder image
                            .error(R.drawable.ic_movie_image) // Error image in case of loading failure
                    )
                    .into(imageViewMoviePoster)
                setImageDrawable(drawable.apply {
                    R.drawable.ic_movie_image
                })
            }
            movieName.text = "Movie : ${movieDetails.title}"
            movieDescription.text = "Description : ${movieDetails.overview}"
            adultRated.text = "Adult Allowed : ${movieDetails.adult.toString()}"

            releasesDate.text = movieDetails.releaseDate?.let {
                "Releases Date : ${it}"
            } ?: run {
                "Releases Date : 31/01/2000"
            }
            imdbRating.text = movieDetails.imdbId?.let {
                "IMDB : ${it}"
            } ?: run {
                "IMDB : 1"
            }

            genres.text = "Genres : " + getGenresList(movieDetails.genres)
            budget.text = "Box Office Budget : ${movieDetails.budget}"
            spokenLanguages.text =
                "Spoken Languages : " + getSpokenLanguages(movieDetails.spokenLanguages)
            originalCountry.text =
                "Original Country : " + getOriginCountry(movieDetails.originCountry)

            visitBtn.setOnClickListener {
                movieDetails.homepage?.let { it1 -> openUrl(it1) }
            }
            shareBtn.setOnClickListener {
                movieDetails.homepage?.let { it1 -> shareLink(it1) }
            }
        }
    }

    private fun openUrl(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun shareLink(url: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(
            intent, null
        )
        startActivity(shareIntent)
    }


    private fun getMovieDetails() {
        moviesViewModel.movieItemId.value?.let {
            moviesViewModel.getMovieDetail(it)
        }
    }
}