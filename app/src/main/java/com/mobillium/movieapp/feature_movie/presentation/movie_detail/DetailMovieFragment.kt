package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.mobillium.movieapp.R
import com.mobillium.movieapp.core.utils.EndPoints
import com.mobillium.movieapp.databinding.FragmentMovieDetailBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import com.mobillium.movieapp.feature_movie.presentation.common.extension.clearImageWithGlide
import com.mobillium.movieapp.feature_movie.presentation.common.extension.getBasePosterPath
import com.mobillium.movieapp.feature_movie.presentation.common.extension.loadImageFromURL
import com.mobillium.movieapp.feature_movie.presentation.common.extension.reformatDate
import com.mobillium.movieapp.feature_movie.presentation.common.extension.showGenericAlertDialog
import com.mobillium.movieapp.feature_movie.presentation.common.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel by viewModels<DetailMovieViewModel>()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)
        observe()

        val args: DetailMovieFragmentArgs by navArgs()
        viewModel.getMovieDetail(args.movieId)
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    handleStateChange(uiState)
                }
            }
        }
    }

    private fun handleStateChange(uiState: MovieDetailFragmentUIState) {
        when (uiState) {
            is MovieDetailFragmentUIState.Init -> Unit
            is MovieDetailFragmentUIState.IsLoading -> handleLoading(uiState.isLoading)
            is MovieDetailFragmentUIState.ShowToast -> handleToastMessage(uiState.message)
            is MovieDetailFragmentUIState.SuccessMovieDetailDetail -> handleMovieDetail(uiState.movieDetailEntity)
            is MovieDetailFragmentUIState.Error -> handleError(uiState.message, uiState.errorCode)
        }
    }

    private fun handleToastMessage(message: String) {
        binding.errorTextView.visibility = View.VISIBLE
        requireContext().showToast(message)
    }

    private fun handleError(errorMessage: String, errorCode: Int) {
        binding.errorTextView.visibility = View.VISIBLE
        requireContext().showGenericAlertDialog(
            message = getString(
                R.string.error_msg_and_error_code_message,
                errorMessage,
                errorCode
            )
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun loadPosterImage(posterPath: String?) {
        if (!posterPath.isNullOrEmpty()) {
            val basePosterPath = posterPath.getBasePosterPath()
            requireContext().loadImageFromURL(binding.itemMovieImage, basePosterPath)
        } else {
            requireContext().clearImageWithGlide(binding.itemMovieImage)
        }
    }

    private fun listenImdbImageClick(imdbId: String) {
        binding.movieItemImdbImage.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(EndPoints.IMDB_URL + imdbId))
            startActivity(browserIntent)
        }
    }

    private fun handleMovieDetail(movieDetailEntity: MovieDetailEntity) {
        binding.movieItemTitle.text = movieDetailEntity.title
        binding.movieItemDescription.text = movieDetailEntity.overview
        binding.movieItemReleaseDate.text = movieDetailEntity.releaseDate.reformatDate()
        binding.movieItemRate.text = String.format("%.1f", movieDetailEntity.voteAverage)

        loadPosterImage(movieDetailEntity.backdropPath)
        listenImdbImageClick(movieDetailEntity.imdbId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}