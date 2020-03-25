package com.aherbel.patagonianpoc.presentation.screens.searchhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aherbel.patagonianpoc.databinding.ListLyricBinding
import com.aherbel.patagonianpoc.domain.Lyric

class LyricListAdapter(private val data: ArrayList<Lyric>) : RecyclerView.Adapter<LyricViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricViewHolder {
        val binding = ListLyricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LyricViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LyricViewHolder, position: Int) {
        val lyric = data[position]
        holder.bind(lyric)
    }
}