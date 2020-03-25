package com.aherbel.patagonianpoc.presentation.screens.searchhistory

import androidx.recyclerview.widget.RecyclerView
import com.aherbel.patagonianpoc.R
import com.aherbel.patagonianpoc.databinding.ListLyricBinding
import com.aherbel.patagonianpoc.domain.Lyric
import com.aherbel.patagonianpoc.presentation.screens.songdetail.SongDetailActivity

class LyricViewHolder(private val binding: ListLyricBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lyric: Lyric?) {
        binding.tvLyricDescription.apply {
            text = binding.root.context.getString(R.string.author_title, lyric?.artist, lyric?.title)
            setOnClickListener {
                if (lyric != null) {
                    SongDetailActivity.start(it.context, lyric)
                }
            }
        }
    }
}