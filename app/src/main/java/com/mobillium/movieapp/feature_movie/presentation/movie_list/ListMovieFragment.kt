package com.mobillium.movieapp.feature_movie.presentation.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.mobillium.movieapp.R
import com.mobillium.movieapp.core.utils.EndPoints
import com.mobillium.movieapp.databinding.FragmentMovieListBinding
import com.mobillium.movieapp.feature_movie.domain.entity.movie.MovieEntity
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.presentation.common.extension.getBaseBackdropPath
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

    private lateinit var navController: NavController
    private lateinit var rvAdapter: MovieRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)
        navController = Navigation.findNavController(view)

        observe()
        getMoviesFromApi()
        recyclerViewDisplay()
        listenSwipeRefreshAction()
    }

    private fun recyclerViewDisplay() {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> setUpRecyclerView(2)
            else -> setUpRecyclerView(1)
        }
    }

    private fun setUpRecyclerView(spanCount: Int) {
        binding.rvMovies.apply {
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

    private fun listenSwipeRefreshAction() {
        binding.swipeListRefreshLayout.setOnRefreshListener {
            getMoviesFromApi()
        }
    }

    private fun getMoviesFromApi() {
        viewModel.getNowPlayingMovies(1)
        viewModel.getUpComingMovies(1)
    }
    private fun setUpSliderView(resultList: List<MovieEntity>) {
        val imageList = ArrayList<SlideModel>()
        for (movieEntity in resultList) {
            val imageUrl = movieEntity.backdropPath.getBaseBackdropPath()
            imageList.add(SlideModel(imageUrl, movieEntity.title))
        }
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
        listenImageSliderClick(resultList)
    }

    private fun listenImageSliderClick(resultList: List<MovieEntity>) {
        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                // do nothing
            }

            override fun onItemSelected(position: Int) {
                navigateMovieDetailFragment(resultList[position].id.toString())
            }
        })
    }

    private fun navigateMovieDetailFragment(movieId: String) {
        val action = ListMovieFragmentDirections.actionListMovieFragmentToDetailMovieFragment(movieId)
        navController.navigate(action)
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
            is MovieListFragmentUIState.ShowToast -> handleToastMessage(uiState.message)
            is MovieListFragmentUIState.SuccessUpcomingMovieList -> handleUpcomingMovie(uiState.responseEntity)
            is MovieListFragmentUIState.SuccessNowPlayingMovieList -> handleNowPlayingMovie(uiState.responseEntity)
            is MovieListFragmentUIState.Error -> handleError(uiState.message, uiState.errorCode)
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

    private fun handleNowPlayingMovie(responseEntity: ResponseEntity) {
        binding.swipeListRefreshLayout.isRefreshing = false
        setUpSliderView(responseEntity.resultList)
    }

    private fun handleUpcomingMovie(responseEntity: ResponseEntity) {
        binding.swipeListRefreshLayout.isRefreshing = false
        rvAdapter.submitList(responseEntity.resultList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}