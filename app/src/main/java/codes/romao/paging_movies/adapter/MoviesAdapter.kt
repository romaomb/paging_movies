package codes.romao.paging_movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.romao.paging_movies.databinding.ItemMovieBinding
import codes.romao.paging_movies.model.Movie
import codes.romao.paging_movies.network.POSTER_URL
import com.bumptech.glide.Glide

class MoviesAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.binding.apply {
            tvTitle.text = item.title
            Glide.with(this.root)
                .load(POSTER_URL + item.poster)
                .centerCrop()
                .into(ivPoster)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun notifyChanges(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}
