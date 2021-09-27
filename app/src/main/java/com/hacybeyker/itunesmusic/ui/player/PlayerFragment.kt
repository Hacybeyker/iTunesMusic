package com.hacybeyker.itunesmusic.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding
    private lateinit var music: Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            music = it.getSerializable(Music::class.java.name) as Music
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        binding.music = music
        return binding.root
    }

    fun playMusic(music: Music) {

    }

    companion object {
        @JvmStatic
        fun newInstance(music: Music) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Music::class.java.name, music)
                }
            }
    }
}