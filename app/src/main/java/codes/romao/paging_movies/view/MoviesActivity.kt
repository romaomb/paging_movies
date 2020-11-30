package codes.romao.paging_movies.view

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import codes.romao.paging_movies.adapter.FooterAdapter
import codes.romao.paging_movies.adapter.MoviesAdapter
import codes.romao.paging_movies.adapter.MoviesPagingAdapter
import codes.romao.paging_movies.adapter.MoviesPagingDataAdapter
import codes.romao.paging_movies.core.dp
import codes.romao.paging_movies.databinding.ActivityMoviesBinding
import codes.romao.paging_movies.viewmodel.MoviesPaging3ViewModel
import codes.romao.paging_movies.viewmodel.MoviesPagingViewModel
import codes.romao.paging_movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    //    private val moviesViewModel: MoviesViewModel by viewModel()
//    private val moviesPagingViewModel: MoviesPagingViewModel by viewModel()
    private val moviesPagingViewModel: MoviesPaging3ViewModel by viewModel()

    //    private val moviesAdapter by lazy { MoviesAdapter(listOf()) }
//    private val moviesPagingAdapter by lazy { MoviesPagingAdapter() }
    private val moviesPagingDataAdapter by lazy { MoviesPagingDataAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        observeMovies()
    }

    private fun setRecyclerView() {
        binding.rvMovies.apply {
            adapter = moviesPagingDataAdapter.withLoadStateFooter(FooterAdapter())
            layoutManager = GridLayoutManager(this@MoviesActivity, 2)
            addItemDecoration(createItemDecoration())
        }
    }

    private fun createItemDecoration() = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val isItemOnLeft = binding.rvMovies.getChildAdapterPosition(view) % 2 == 0
            val marginMin = 8.dp(resources)
            val marginMax = 16.dp(resources)
            outRect.apply {
                left = if (isItemOnLeft) marginMax else marginMin
                right = if (isItemOnLeft) marginMin else marginMax
                top = marginMin
                bottom = marginMin
            }
        }
    }

    private fun observeMovies() {
        moviesPagingViewModel.movies.observe(this, Observer {
            moviesPagingDataAdapter.submitData(lifecycle, it)
        })
    }
}