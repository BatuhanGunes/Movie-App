package com.mobillium.movieapp.feature_movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobillium.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}