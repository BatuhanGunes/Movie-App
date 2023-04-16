package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mobillium.movieapp.R
import com.mobillium.movieapp.databinding.FragmentMovieDetailBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
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
        viewModel.getMovieDetail("TODO")
        observe()
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
            is MovieDetailFragmentUIState.ShowToast -> requireContext().showToast(uiState.message)
            is MovieDetailFragmentUIState.SuccessMovieDetailDetail -> handleMovieDetail(uiState.movieDetailEntity)
            is MovieDetailFragmentUIState.Error -> handleError(uiState.message, uiState.errorCode)
        }
    }

    private fun handleError(errorMessage: String, errorCode: Int) {
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

    private fun handleMovieDetail(movieDetailEntity: MovieDetailEntity) {
        // TODO:
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}