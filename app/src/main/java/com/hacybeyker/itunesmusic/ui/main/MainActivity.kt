package com.hacybeyker.itunesmusic.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.hacybeyker.entities.Music
import com.hacybeyker.itunesmusic.R
import com.hacybeyker.itunesmusic.databinding.ActivityMainBinding
import com.hacybeyker.itunesmusic.ui.detail.DetailMusicActivity
import com.hacybeyker.itunesmusic.ui.main.adapter.MusicAdapter
import com.hacybeyker.itunesmusic.utils.hideKeyboard
import jp.wasabeef.recyclerview.animators.LandingAnimator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MusicAdapter.OnItemSelectedListener,
    AdapterView.OnItemClickListener,
    TextView.OnEditorActionListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MusicViewModel by inject()
    private val suggestionViewModel: SuggestionViewModel by inject()
    private val adapter: MusicAdapter by lazy { MusicAdapter(this) }
    private val adapterAutoComplete: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter
        binding.adapterAutoComplete = adapterAutoComplete
        binding.executePendingBindings()

        binding.mainAutoCompleteSearch.onItemClickListener = this
        binding.mainAutoCompleteSearch.setOnEditorActionListener(this)
        binding.mainRecyclerMusics.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerMusics.itemAnimator = LandingAnimator().apply {
            addDuration = 400
        }

        viewModel.fetchMusic(term = "mana", limit = 20, page = 1)
        viewModel.musicSuccess.observe(this) {
            it?.let {
                adapter.items = it
            }
        }

        suggestionViewModel.fetchSuggestion()

        suggestionViewModel.suggestionLiveData.observe(this) {
            adapterAutoComplete.clear()
            adapterAutoComplete.addAll(it)
        }
    }

    override fun onItemSelected(item: Music, view: View?) {
        DetailMusicActivity.newInstance(this, item, view)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.let {
            val term = it.getItemAtPosition(position).toString()
            search(term)
        }
    }

    private fun search(query: String) {
        this.hideKeyboard()
        val term = "%$query%"
        suggestionViewModel.saveSuggestion(term = query)
        viewModel.fetchMusic(term = term, limit = 20, page = 1)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (EditorInfo.IME_ACTION_SEARCH == actionId)
            search(query = v?.text.toString())
        return true
    }
}