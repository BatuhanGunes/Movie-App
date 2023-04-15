package com.mobillium.movieapp.feature_movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mobillium.movieapp.R

class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}