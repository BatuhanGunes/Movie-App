package com.mobillium.movieapp.feature_movie.presentation.movie_list.adapters

import androidx.recyclerview.widget.DiffUtil
import com.mobillium.movieapp.feature_movie.domain.entity.movie.MovieEntity

class DiffUtilCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.title == newItem.title
    }
}