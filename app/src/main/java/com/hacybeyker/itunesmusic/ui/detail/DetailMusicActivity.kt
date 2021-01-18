package com.hacybeyker.itunesmusic.ui.detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityDetailMusicBinding
import com.hacybeyker.itunesmusic.ui.detail.adapter.MusicDetailAdapter
import jp.wasabeef.recyclerview.animators.LandingAnimator

class DetailMusicActivity : AppCompatActivity(), MusicDetailAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityDetailMusicBinding
    private val viewModel: MusicDetailViewModel by lazy {
        ViewModelProvider(this@DetailMusicActivity).get(MusicDetailViewModel::class.java)
    }
    private val adapter: MusicDetailAdapter by lazy { MusicDetailAdapter(this) }
    private lateinit var music: Music
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        fun newInstance(
            activity: Activity,
            music: Music,
            imageView: View?
        ) {
            val intent = Intent(activity, DetailMusicActivity::class.java)
            intent.putExtra(Music::class.java.name, music)
            if (imageView != null) {
                ViewCompat.setTransitionName(imageView, music.trackId.toString())
                val p1 =
                    Pair.create<View, String>(imageView, ViewCompat.getTransitionName(imageView))
                val options = ActivityOptions.makeSceneTransitionAnimation(activity, p1)
                activity.startActivity(intent, options.toBundle())
            } else {
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_music)
        binding.music = music
        binding.adapter = adapter
        binding.executePendingBindings()

        binding.detailMusicImageArt.transitionName = music.trackId.toString()
        binding.detailRecyclerMusic.itemAnimator = LandingAnimator().apply {
            addDuration = 400
        }

        viewModel.fetchFetchMusicByAlbum(music.collectionId).observe(this) {
            adapter.items = it
        }
    }

    private fun getIntentData() {
        intent?.let { it ->
            it.getSerializableExtra(Music::class.java.name)?.let { music = it as Music }
        }
    }

    override fun onItemSelected(item: Music) {
        val uri = Uri.parse(item.previewUrl)
        if (!::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer()
        }
        executeSong(uri)
    }

    private fun executeSong(uri: Uri) {
        if (isPlaying()) {
            mediaPlayer.isLooping = false
            mediaPlayer.stop()
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer.setDataSource(this, uri)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    private fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized)
            mediaPlayer.release()
    }
}