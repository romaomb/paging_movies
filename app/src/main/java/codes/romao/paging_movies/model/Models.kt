package codes.romao.paging_movies.model

import com.squareup.moshi.Json

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

data class Movie(
    val id: Int,
    val title: String,
    @field:Json(name = "poster_path") val poster: String,
)