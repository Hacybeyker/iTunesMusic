package com.hacybeyker.itunesmusic.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hacybeyker.entities.Music

class MusicDiffUtil(
) : DiffUtil.ItemCallback<Music>() {
    /*override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].trackId == newList[newItemPosition].trackId
                && oldList[oldItemPosition].trackName == newList[newItemPosition].trackName
    }*/

    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }
}