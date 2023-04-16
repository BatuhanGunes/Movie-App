package com.mobillium.movieapp.feature_movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobillium.movieapp.R
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearDiskCache
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearGlideMemory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearGlideMemory()
        clearDiskCache()
    }
}