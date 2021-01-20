package com.hacybeyker.itunesmusic.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hacybeyker.entities.Music

class MusicDiffUtil(
    private val oldList: List<Music>,
    private val newList: List<Music>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].trackId == newList[newItemPosition].trackId
                && oldList[oldItemPosition].trackName == newList[newItemPosition].trackName
    }
}