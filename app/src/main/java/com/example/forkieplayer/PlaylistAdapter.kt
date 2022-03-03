package com.example.forkieplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.databinding.RecyclerPlaylistItemBinding

class PlaylistViewHolder(val binding: RecyclerPlaylistItemBinding): RecyclerView.ViewHolder(binding.root)

class PlaylistAdapter(val datas: List<PlaylistData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return datas.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlaylistViewHolder(RecyclerPlaylistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PlaylistViewHolder).binding
        binding.ivImage.setImageResource(datas.get(position).image)
        binding.tvTitle.text = datas.get(position).title
        binding.tvCount.text = datas.get(position).count.toString()
    }
}