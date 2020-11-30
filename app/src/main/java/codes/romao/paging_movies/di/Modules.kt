package codes.romao.paging_movies.di

import codes.romao.paging_movies.data.MoviesDataSource
import codes.romao.paging_movies.data.MoviesPagingSource
import codes.romao.paging_movies.network.createService
import codes.romao.paging_movies.viewmodel.MoviesPaging3ViewModel
import codes.romao.paging_movies.viewmodel.MoviesPagingViewModel
import codes.romao.paging_movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { createService() }
//    single { MoviesDataSource(get()) }
    single { MoviesPagingSource(get()) }

//    viewModel { MoviesViewModel(get()) }
//    viewModel { MoviesPagingViewModel(get()) }
    viewModel { MoviesPaging3ViewModel(get()) }
}