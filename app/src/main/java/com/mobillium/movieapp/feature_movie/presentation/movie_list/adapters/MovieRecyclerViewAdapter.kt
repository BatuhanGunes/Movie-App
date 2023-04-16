package com.mobillium.movieapp.feature_movie.presentation.movie_list.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.movieapp.databinding.CardViewMovieItemBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie.MovieEntity
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearImageWithGlide
import com.mobillium.movieapp.feature_movie.presentation.common.extension.getBasePosterPath
import com.mobillium.movieapp.feature_movie.presentation.common.extension.loadImageFromURL
import com.mobillium.movieapp.feature_movie.presentation.common.extension.reformatDate
import com.mobillium.movieapp.feature_movie.presentation.movie_list.ListMovieFragmentDirections

class MovieRecyclerViewAdapter(private val currentList: MutableList<MovieEntity>) :
    ListAdapter<MovieEntity, MovieRecyclerViewAdapter.MovieViewHolder>(DiffUtilCallback()) {

    inner class MovieViewHolder(private val itemBinding: CardViewMovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: MovieEntity) {
            itemBinding.movieItemTitle.text = movie.title
            itemBinding.movieItemDescription.text = movie.overview
            itemBinding.movieItemUpdateDate.text = movie.releaseDate.reformatDate()

            val rootContext: Context = itemBinding.root.context
            if (!movie.posterPath.isNullOrEmpty()) {
                val basePosterPath = movie.posterPath.getBasePosterPath()
                rootContext.loadImageFromURL(itemBinding.itemMovieImage, basePosterPath)
            } else {
                rootContext.clearImageWithGlide(itemBinding.itemMovieImage)
            }

            itemBinding.root.setOnClickListener {
                val movieId: String = movie.id.toString()
                val action =
                    ListMovieFragmentDirections.actionListMovieFragmentToDetailMovieFragment(movieId)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun addMovieList(movies: List<MovieEntity>) {
        currentList.addAll(movies)
        this.submitList(currentList)
        val previousItemSize = currentList.size
        notifyItemRangeInserted(previousItemSize, movies.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            CardViewMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(currentList[position])

}