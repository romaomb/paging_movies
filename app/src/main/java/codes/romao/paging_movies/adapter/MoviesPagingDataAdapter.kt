package codes.romao.paging_movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import codes.romao.paging_movies.databinding.ItemMovieBinding
import codes.romao.paging_movies.model.Movie
import codes.romao.paging_movies.network.POSTER_URL
import com.bumptech.glide.Glide

class MoviesPagingDataAdapter :
    PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MoviesPagingAdapter.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        return MoviesAdapter.MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.apply {
                tvTitle.text = item.title
                Glide.with(this.root)
                    .load(POSTER_URL + item.poster)
                    .centerCrop()
                    .into(ivPoster)
            }
        }
    }
}
