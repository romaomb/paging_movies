package codes.romao.paging_movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.romao.paging_movies.databinding.ItemFooterBinding

class FooterAdapter : LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        return FooterViewHolder(
            ItemFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.binding.pbLoading.isVisible = loadState is LoadState.Loading
    }

    class FooterViewHolder(val binding: ItemFooterBinding) : RecyclerView.ViewHolder(binding.root)
}