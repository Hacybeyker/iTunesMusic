package com.hacybeyker.itunesmusic.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityMainBinding
import com.hacybeyker.itunesmusic.ui.detail.DetailMusicActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MusicAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MusicViewModel by inject()
    private val adapter: MusicAdapter by lazy { MusicAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter
        binding.executePendingBindings()

        viewModel.fetchMusic
        viewModel.musicSuccess.observe(this) {
            it?.let {
                adapter.items = it
            }
        }
    }

    override fun onItemSelected(item: Music) {
        DetailMusicActivity.newInstance(this, item)
    }
}