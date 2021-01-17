package com.hacybeyker.itunesmusic.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityDetailMusicBinding
import com.hacybeyker.itunesmusic.ui.main.MusicAdapter

class DetailMusicActivity : AppCompatActivity(), MusicAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityDetailMusicBinding
    private val adapter: MusicAdapter by lazy { MusicAdapter(this) }
    private lateinit var music: Music

    companion object {
        fun newInstance(activity: Activity, music: Music) {
            val intent = Intent(activity, DetailMusicActivity::class.java)
            intent.putExtra(Music::class.java.name, music)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_music)
        binding.music = music
        binding.adapter = adapter
        binding.executePendingBindings()
    }

    private fun getIntentData() {
        intent?.let {
            it.getSerializableExtra(Music::class.java.name)?.let { music = it as Music }
        }
    }

    override fun onItemSelected(item: Music) {
        TODO("Not yet implemented")
    }
}