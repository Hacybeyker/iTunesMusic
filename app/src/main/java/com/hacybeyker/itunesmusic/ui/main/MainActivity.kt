package com.hacybeyker.itunesmusic.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.hacybeyker.itunesmusic.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MusicViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchMusic
        viewModel.musicSuccess.observe(this) {
            for (item in it)
                Log.d("TAG", "Here - onCreate: ${item.artistName}")
        }
    }
}