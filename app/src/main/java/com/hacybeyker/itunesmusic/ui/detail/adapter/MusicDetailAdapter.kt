package com.hacybeyker.itunesmusic.ui.detail.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.databinding.RecyclerItemMusicAlbumBinding
import com.hacybeyker.itunesmusic.ui.main.adapter.MusicDiffUtil

class MusicDetailAdapter(private val onItemSelectedListener: OnItemSelectedListener) :
    RecyclerView.Adapter<MusicDetailAdapter.MusicDetailViewHolder>() {

    var items = emptyList<Music>()
        set(value) {
            val musicDiffUtil = MusicDiffUtil(this.items, value)
            val musicDiffResult = DiffUtil.calculateDiff(musicDiffUtil)
            field = value
            musicDiffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDetailViewHolder {
        return MusicDetailViewHolder.from(parent, onItemSelectedListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MusicDetailViewHolder, position: Int) {
        holder.bind(items[position], position.plus(1))
    }

    class MusicDetailViewHolder(
        private val binding: RecyclerItemMusicAlbumBinding,
        private val onItemSelectedListener: OnItemSelectedListener
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup,
                onItemSelectedListener: OnItemSelectedListener
            ): MusicDetailViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemMusicAlbumBinding.inflate(layoutInflater, parent, false)
                return MusicDetailViewHolder(binding, onItemSelectedListener)
            }
        }

        fun bind(item: Music, position: Int) {
            binding.music = item
            binding.position = position
            binding.onItemSelected = onItemSelectedListener
            binding.executePendingBindings()
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: Music)
    }
}