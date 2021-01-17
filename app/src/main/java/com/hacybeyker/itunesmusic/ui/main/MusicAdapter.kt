package com.hacybeyker.itunesmusic.ui.main;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.databinding.RecyclerItemMusicBinding

class MusicAdapter(private val onItemSelectedListener: OnItemSelectedListener) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    var items: List<Music> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder.from(parent, onItemSelectedListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MusicViewHolder(
        private val binding: RecyclerItemMusicBinding,
        private val onItemSelectedListener: OnItemSelectedListener
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup,
                onItemSelectedListener: OnItemSelectedListener
            ): MusicViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemMusicBinding.inflate(layoutInflater, parent, false)
                return MusicViewHolder(binding, onItemSelectedListener)
            }
        }

        fun bind(item: Music) {
            binding.music= item
            binding.onItemSelected = onItemSelectedListener
            binding.executePendingBindings()
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: Music)
    }
}