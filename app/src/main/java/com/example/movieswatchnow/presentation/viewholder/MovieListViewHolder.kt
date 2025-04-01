package com.example.movieswatchnow.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieswatchnow.databinding.ViewItemCardBinding
import com.example.movieswatchnow.domain.Movie


open class MovieListViewHolder(val binding: ViewItemCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bindDataToView(
        movieItem: Movie?,
        position: Int
    ) {
        with(binding) {
            imageViewMoviePoster.apply {

                val imageUrl =
                    "https://image.tmdb.org/t/p/w500/${movieItem?.poster_path}" // Replace with your image URL

                // Use Glide to load the image from the URL
                Glide.with(this)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(com.example.movieswatchnow.R.drawable.ic_movie_image) // Placeholder image
                            .error(com.example.movieswatchnow.R.drawable.ic_movie_image) // Error image in case of loading failure
                    )
                    .into(imageViewMoviePoster)
                setImageDrawable(drawable.apply {
                    com.example.movieswatchnow.R.drawable.ic_movie_image
                })
            }
            movieName.text = movieItem?.title
            saveMovie.visibility= View.VISIBLE

        }
    }
}