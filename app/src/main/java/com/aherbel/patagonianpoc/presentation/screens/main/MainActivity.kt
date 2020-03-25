package com.aherbel.patagonianpoc.presentation.screens.main

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.aherbel.patagonianpoc.R
import com.aherbel.patagonianpoc.databinding.ActivityMainBinding
import com.aherbel.patagonianpoc.domain.Lyric
import com.aherbel.patagonianpoc.presentation.mvp.BaseMVPActivity
import com.aherbel.patagonianpoc.presentation.screens.searchhistory.SearchHistoryActivity
import com.aherbel.patagonianpoc.presentation.screens.songdetail.SongDetailActivity

class MainActivity : BaseMVPActivity<MainMVPPresenter, MainMVPView>(), MainMVPView {

    private lateinit var binding: ActivityMainBinding

    private var lastLyricSearched: Lyric? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

        presenter.attachView(this)
    }

    private fun setupViews() {
        binding.tilAuthor.apply {
            error = getString(R.string.artist_field_error)
            isErrorEnabled = false
        }
        binding.tilSong.apply {
            error = getString(R.string.song_field_error)
            isErrorEnabled = false
        }

        binding.etAuthor.addTextChangedListener { text: Editable? ->
            if (text?.isEmpty() == true) {
                binding.tilAuthor.error = getString(R.string.artist_field_error)
            } else {
                binding.tilAuthor.helperText = getString(R.string.artist_helper_text)
            }
        }

        binding.etSong.addTextChangedListener { text ->
            if (text?.isEmpty() == true) {
                binding.tilSong.error = getString(R.string.song_field_error)
            } else {
                binding.tilSong.helperText = getString(R.string.song_helper_text)
            }
        }

        binding.btnSearch.setOnClickListener {
            val author = binding.etAuthor.text.toString()
            val songTitle = binding.etSong.text.toString()

            if (author.isEmpty() || songTitle.isEmpty()) {
                return@setOnClickListener
            }

            binding.btnSearch.isEnabled = false
            presenter.searchLyric(author, songTitle)
        }

        binding.tvPreviousSearch.setOnClickListener {
            lastLyricSearched?.let { lyric ->
                SongDetailActivity.start(this, lyric)
            }
        }

        binding.tvHistory.setOnClickListener {
            presenter.clickSearchHistory()
        }
    }

    override fun createPresenter(): MainMVPPresenter = MainMVPPresenter()

    override fun onLyricRetrieved(lyric: Lyric?) {
        lastLyricSearched = lyric

        lastLyricSearched?.let { lastLyric ->
            binding.tvPreviousSearch.apply {
                text = getString(R.string.previous_search, lastLyric.artist, lastLyric.title)
                visibility = View.VISIBLE
            }
        } ?: run {
            binding.tvPreviousSearch.visibility = View.GONE
        }

        binding.btnSearch.isEnabled = true
        binding.tvLyric.text = lyric?.lyric
        binding.scrollView.visibility = View.VISIBLE
    }

    override fun onSearchHistoryClicked(lyricsSearchHistory: ArrayList<Lyric>) {
        SearchHistoryActivity.start(this, lyricsSearchHistory)
    }

    override fun onError(errorMessage: String) {
        binding.btnSearch.isEnabled = true
        binding.scrollView.visibility = View.GONE
        binding.tvErrorMessage.apply {
            text = errorMessage
            visibility = View.VISIBLE
        }
    }

    override fun showLoading() {
        binding.progress.visibility = View.VISIBLE
        binding.scrollView.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE
    }

}
