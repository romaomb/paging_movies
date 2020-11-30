package codes.romao.paging_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import codes.romao.paging_movies.data.MoviesPagingSource

class MoviesPaging3ViewModel(
    private val moviesPagingSource: MoviesPagingSource
) : ViewModel() {

    val movies = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { moviesPagingSource }
    ).liveData.cachedIn(viewModelScope)

}
