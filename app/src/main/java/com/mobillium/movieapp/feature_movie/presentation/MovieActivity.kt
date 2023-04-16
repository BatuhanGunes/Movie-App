package com.mobillium.movieapp.feature_movie.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mobillium.movieapp.R
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearDiskCache
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearGlideMemory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        makeStatusBarTransparent()
        setContentView(R.layout.activity_movie)
    }

    @Suppress("DEPRECATION")
    private fun makeStatusBarTransparent() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearGlideMemory()
        clearDiskCache()
    }
}