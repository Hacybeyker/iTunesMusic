package com.hacybeyker.itunesmusic.ui.detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityDetailMusicBinding
import com.hacybeyker.itunesmusic.ui.detail.adapter.MusicDetailAdapter
import com.hacybeyker.itunesmusic.ui.detail.viewmodel.MusicDetailViewModel
import com.hacybeyker.itunesmusic.ui.player.PlayerFragment
import com.hacybeyker.itunesmusic.utils.Person
import com.hacybeyker.repository.network.exception.EmptyError
import com.hacybeyker.repository.network.exception.GenericException
import com.hacybeyker.repository.network.exception.NetworkException
import jp.wasabeef.recyclerview.animators.LandingAnimator

class DetailMusicActivity : AppCompatActivity(), MusicDetailAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityDetailMusicBinding
    private val viewModel: MusicDetailViewModel by lazy {
        ViewModelProvider(this@DetailMusicActivity).get(MusicDetailViewModel::class.java)
    }
    private val adapter: MusicDetailAdapter by lazy { MusicDetailAdapter(this) }
    private lateinit var playerFragment: PlayerFragment

    private lateinit var music: Music
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        fun newInstance(
            activity: Activity,
            music: Music,
            person: Person,
            imageView: View?
        ) {
            val intent = Intent(activity, DetailMusicActivity::class.java)
            intent.putExtra(Music::class.java.name, music)
            val bundle = Bundle()
            bundle.putString("String", "Value")
            intent.putExtra("bundle", bundle)
            intent.putExtra(Person::class.java.name, person)
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
        binding.detailRecyclerMusic.itemAnimator = LandingAnimator().apply { addDuration = 400 }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.fetchFetchMusicByAlbum(music.collectionId).observe(this) {
            adapter.items = it
        }

        viewModel.errorLiveData.observe(this) {
            showError(it)
        }
    }

    private fun showError(error: Throwable) {
        when (error) {
            is GenericException -> {
                Toast.makeText(this, error.title, Toast.LENGTH_LONG).show()
            }
            is EmptyError -> {
                adapter.items = arrayListOf()
                Toast.makeText(this, error.title, Toast.LENGTH_LONG).show()
            }
            is NetworkException -> {
                Toast.makeText(this, error.title, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getIntentData() {
        intent?.let { it ->
            it.getSerializableExtra(Music::class.java.name)?.let { music = it as Music }
            Log.d("TAG", "Here - getIntentData: ${it.getBundleExtra("bundle")}")
            val algo = it.getBundleExtra("bundle")
            Log.d("TAG", "Here - getIntentData: ${algo?.getString("String")}")
            val person = intent.getParcelableExtra<Person>(Person::class.java.name)
            Log.d("TAG", "Here - getIntentData: $person")
        }
    }

    override fun onItemSelected(item: Music) {
        playerFragment = PlayerFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
            .add(R.id.detailFragmentContainer, playerFragment, PlayerFragment::class.java.name)
            .commit()
        playerFragment.playMusic(item)
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