package com.hacybeyker.itunesmusic.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityMainBinding
import com.hacybeyker.itunesmusic.ui.detail.DetailMusicActivity
import com.hacybeyker.itunesmusic.utils.hideKeyboard
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MusicAdapter.OnItemSelectedListener,
    SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MusicViewModel by inject()
    private val adapter: MusicAdapter by lazy { MusicAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter
        binding.executePendingBindings()

        viewModel.fetchMusic(term = "mana", limit = 20, page = 1)
        viewModel.musicSuccess.observe(this) {
            it?.let {
                adapter.items = it
            }
        }
    }

    override fun onItemSelected(item: Music) {
        DetailMusicActivity.newInstance(this, item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { search(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun search(query: String) {
        this.hideKeyboard()
        val term = "%$query%"
        viewModel.fetchMusic(term = term, limit = 20, page = 1)
    }
}