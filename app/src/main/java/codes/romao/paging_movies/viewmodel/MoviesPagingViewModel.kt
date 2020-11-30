package codes.romao.paging_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import codes.romao.paging_movies.data.MoviesDataSource
import codes.romao.paging_movies.model.Movie

class MoviesPagingViewModel(
    private val moviesDataSource: MoviesDataSource
) : ViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .build()

    private val moviesDataSourceFactory = object : DataSource.Factory<Int, Movie>() {
        override fun create(): DataSource<Int, Movie> = moviesDataSource
    }

    val movies = LivePagedListBuilder(moviesDataSourceFactory, pagedListConfig).build()
}