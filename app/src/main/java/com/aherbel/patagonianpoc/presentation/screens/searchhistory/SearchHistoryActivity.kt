package com.aherbel.patagonianpoc.presentation.screens.searchhistory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aherbel.patagonianpoc.databinding.ActivitySearchHistoryBinding
import com.aherbel.patagonianpoc.domain.Lyric

class SearchHistoryActivity : AppCompatActivity() {

    companion object {
        private const val LYRICS_LIST = "LYRICS_LIST"

        fun start(context: Context, lyricsList: ArrayList<Lyric>) {
            val intent = Intent(context, SearchHistoryActivity::class.java).apply {
                putExtra(LYRICS_LIST, lyricsList)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySearchHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lyricsList: ArrayList<Lyric> = (intent?.extras?.getSerializable(LYRICS_LIST) as? ArrayList<Lyric>) ?: ArrayList()

        if (lyricsList.isEmpty()) {
            binding.tvEmptyList.visibility = View.VISIBLE
            binding.rvLyrics.visibility = View.GONE
        } else {
            binding.tvEmptyList.visibility = View.GONE
            binding.rvLyrics.apply {
                adapter = LyricListAdapter(lyricsList)
                visibility = View.VISIBLE
            }
        }
    }
}
