package codes.romao.paging_movies.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import codes.romao.paging_movies.model.Movie
import codes.romao.paging_movies.network.MoviesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MoviesDataSource(
    private val moviesService: MoviesService
) : PageKeyedDataSource<Int, Movie>() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var currentPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        scope.launch {
            val moviesResponse = moviesService.upcomingMovies(currentPage)
            callback.onResult(moviesResponse.results, null, ++currentPage)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) =
        fetchMovies(false, callback)

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) =
        fetchMovies(true, callback)

    private fun fetchMovies(loadNextPage: Boolean, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            val moviesResponse = moviesService.upcomingMovies(currentPage)
            callback.onResult(
                moviesResponse.results,
                if (loadNextPage) ++currentPage else --currentPage
            )
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}
