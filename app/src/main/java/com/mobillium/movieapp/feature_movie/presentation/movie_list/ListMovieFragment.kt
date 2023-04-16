package com.mobillium.movieapp.feature_movie.presentation.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mobillium.movieapp.R
import com.mobillium.movieapp.databinding.FragmentMovieListBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.presentation.common.extension.showGenericAlertDialog
import com.mobillium.movieapp.feature_movie.presentation.common.extension.showToast
import com.mobillium.movieapp.feature_movie.presentation.movie_list.adapters.MovieRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ListMovieFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel: ListMovieViewModel by viewModels()
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: MovieRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)

        recyclerViewDisplay()
        getMoviesFromApi()
        observe()
    }

    private fun recyclerViewDisplay() {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> setUpRecyclerView(2)
            else -> setUpRecyclerView(1)
        }
    }

    private fun setUpRecyclerView(spanCount: Int) {
        binding.rvNote.apply {
            layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            rvAdapter = MovieRecyclerViewAdapter()
            adapter = rvAdapter
            postponeEnterTransition(300L, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun getMoviesFromApi() {
        viewModel.getNowPlayingMovies(1)
        viewModel.getUpComingMovies(1)
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

    private fun handleStateChange(uiState: MovieListFragmentUIState) {
        when (uiState) {
            is MovieListFragmentUIState.Init -> Unit
            is MovieListFragmentUIState.IsLoading -> handleLoading(uiState.isLoading)
            is MovieListFragmentUIState.ShowToast -> requireContext().showToast(uiState.message)
            is MovieListFragmentUIState.SuccessUpcomingMovieList -> handleUpcomingMovie(uiState.responseEntity)
            is MovieListFragmentUIState.SuccessNowPlayingMovieList -> handleNowPlayingMovie(uiState.responseEntity)
            is MovieListFragmentUIState.Error -> handleError(uiState.message, uiState.errorCode)
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

    private fun handleNowPlayingMovie(responseEntity: ResponseEntity) {
        // TODO:
    }

    private fun handleUpcomingMovie(responseEntity: ResponseEntity) {
        rvAdapter.submitList(responseEntity.resultList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}