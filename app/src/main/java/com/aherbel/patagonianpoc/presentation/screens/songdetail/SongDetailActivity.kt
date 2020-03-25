package com.aherbel.patagonianpoc.presentation.screens.songdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aherbel.patagonianpoc.databinding.ActivitySongDetailBinding
import com.aherbel.patagonianpoc.domain.Lyric

class SongDetailActivity : AppCompatActivity() {

    companion object {
        private const val LYRIC: String = "LYRIC"

        fun start(context: Context, lyric: Lyric) {
            val intent = Intent(context, SongDetailActivity::class.java).apply {
                putExtra(LYRIC, lyric)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySongDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lyric = intent?.extras?.getSerializable(LYRIC) as? Lyric

        binding.tvAuthor.text = lyric?.artist
        binding.tvTitle.text = lyric?.title
        binding.tvLyric.text = lyric?.lyric
    }
}
