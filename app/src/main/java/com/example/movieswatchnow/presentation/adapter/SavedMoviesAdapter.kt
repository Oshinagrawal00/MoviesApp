package com.example.movieswatchnow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieswatchnow.databinding.ViewItemCardBinding
import com.example.movieswatchnow.domain.Movie
import com.example.movieswatchnow.presentation.viewholder.SavedMovieListViewHolder

class SavedMoviesAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(diffCallback) {

    private var movieList: List<Movie>? = null

    val adapter by lazy { getAdapterContext() }
    var onShipmentCardClickListener: ((movie: Movie?) -> Unit)? = null
    var onButtonClickListner: ((movie: Movie?) -> Unit)? = null
    private fun getAdapterContext(): RecyclerView.Adapter<RecyclerView.ViewHolder> = this


    fun setMovieList(moviesList: List<Movie>) {
        this.movieList = moviesList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SavedMovieListViewHolder(
            ViewItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = movieList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movieList?.get(position)
        with(holder as SavedMovieListViewHolder) {

            bindDataToView(
                movie,
                position
            )
        }
        holder.binding.imageViewMoviePoster.setOnClickListener {
            onShipmentCardClickListener?.invoke(movie)
        }
        holder.binding.saveMovie.setOnClickListener {
            onButtonClickListner?.invoke(movie)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return true
                //oldItem.orderNo == newItem.orderNo
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}