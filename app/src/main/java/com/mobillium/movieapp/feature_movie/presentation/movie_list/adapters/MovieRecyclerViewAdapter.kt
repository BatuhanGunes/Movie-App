package com.mobillium.movieapp.feature_movie.presentation.movie_list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.movieapp.databinding.CardViewMovieItemBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie.MovieEntity
import com.mobillium.movieapp.feature_movie.presentation.movie_list.ListMovieFragmentDirections

class MovieRecyclerViewAdapter :
    ListAdapter<MovieEntity, MovieRecyclerViewAdapter.MovieViewHolder>(DiffUtilCallback()) {

    inner class MovieViewHolder(private val itemBinding: CardViewMovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: MovieEntity) {
            //itemBinding.itemMovieImage = movie.posterPath todo
            itemBinding.movieItemTitle.text = movie.title
            itemBinding.movieItemDescription.text = movie.overview
            itemBinding.movieItemUpdateDate.text = movie.releaseDate
            itemBinding.root.setOnClickListener {
                val movieId: String = movie.id.toString()
                val action = ListMovieFragmentDirections.actionListMovieFragmentToDetailMovieFragment(movieId)
                Navigation.findNavController(it).navigate(action)
            }
        }
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
        holder.bind(getItem(position))

}