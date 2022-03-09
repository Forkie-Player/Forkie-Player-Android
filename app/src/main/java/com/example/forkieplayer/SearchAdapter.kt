package com.example.forkieplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.databinding.RecyclerSearchItemBinding

class SearchViewHolder(val binding: RecyclerSearchItemBinding): RecyclerView.ViewHolder(binding.root)

class SearchAdapter(val datas: List<SearchData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return datas.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(RecyclerSearchItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SearchViewHolder).binding
        binding.ivThumbnail.setImageResource(datas.get(position).thumbnailImg)
        binding.tvTitle.text = datas.get(position).title
        binding.ivProfile.setImageResource(datas.get(position).profileImg)
        binding.tvName.text = datas.get(position).name
    }
}