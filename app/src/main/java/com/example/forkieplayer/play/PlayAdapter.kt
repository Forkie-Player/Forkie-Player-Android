package com.example.forkieplayer.play

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.databinding.RecyclerVideoListItemBinding

class PlayViewHolder(val binding: RecyclerVideoListItemBinding): RecyclerView.ViewHolder(binding.root)

class PlayAdapter(val playData: List<PlayData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return playData.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return PlayViewHolder(RecyclerVideoListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PlayViewHolder).binding
        binding.ivThumbnail.setImageResource(playData[position].thumbnailImg)
        binding.tvTitle.text = playData[position].title
        binding.tvName.text = playData[position].name
    }
}