package codes.romao.paging_movies.data

import androidx.paging.PagingSource
import codes.romao.paging_movies.model.Movie
import codes.romao.paging_movies.network.MoviesService
import kotlinx.coroutines.delay

class MoviesPagingSource(
    private val moviesService: MoviesService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        var currentPage = params.key ?: 1
        delay(5000)
        val moviesResponse = moviesService.upcomingMovies(currentPage)
        return LoadResult.Page(moviesResponse.results, params.key?.minus(1), ++currentPage)
    }
}
