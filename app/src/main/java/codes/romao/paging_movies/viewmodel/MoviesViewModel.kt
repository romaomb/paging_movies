package codes.romao.paging_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import codes.romao.paging_movies.network.MoviesService
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val moviesService: MoviesService) : ViewModel() {

    private val currentPage = 1

    val movies = liveData(Dispatchers.IO) {
        val moviesResponse = moviesService.upcomingMovies(currentPage)
        emit(moviesResponse.results)
    }

}