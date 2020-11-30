package codes.romao.paging_movies.network

import codes.romao.paging_movies.BuildConfig
import codes.romao.paging_movies.model.MovieResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val API_URL = "https://api.themoviedb.org/3/"
const val POSTER_URL = "https://image.tmdb.org/t/p/w342"
const val TIMEOUT = 20L

interface MoviesService {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(@Query("page") page: Int): MovieResponse
}

fun createService(): MoviesService =
    Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(createOkHttp())
        .build()
        .create(MoviesService::class.java)

fun createOkHttp(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(createHeader())
        .addInterceptor(createLogger())
        .build()

fun createHeader() = Interceptor { chain ->
    val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter("api_key", BuildConfig.API_KEY)
        .addQueryParameter("language", "en-US")
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

fun createLogger() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
