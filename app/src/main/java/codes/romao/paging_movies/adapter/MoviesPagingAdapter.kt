package codes.romao.paging_movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import codes.romao.paging_movies.databinding.ItemMovieBinding
import codes.romao.paging_movies.model.Movie
import codes.romao.paging_movies.network.POSTER_URL
import com.bumptech.glide.Glide

class MoviesPagingAdapter :
    PagedListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MOVIE_COMPARATOR) {

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

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}