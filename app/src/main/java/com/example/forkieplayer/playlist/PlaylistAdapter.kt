package com.example.forkieplayer.playlist

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.databinding.RecyclerPlaylistItemBinding

class PlaylistViewHolder(val binding: RecyclerPlaylistItemBinding): RecyclerView.ViewHolder(binding.root)

class PlaylistAdapter(val datas: List<PlaylistData>, val fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context

    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return datas.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return PlaylistViewHolder(RecyclerPlaylistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PlaylistViewHolder).binding
        binding.ivImage.setImageResource(datas.get(position).image)
        binding.tvTitle.text = datas.get(position).title
        binding.tvCount.text = datas.get(position).count.toString()

        // 리사이클러뷰 아이템 롱클릭시 플레이리스트 편집 fragment 뜨게 함
        holder.itemView.setOnLongClickListener {
            FragmentPlaylistEditBottomSheet.newInstance().show(
                fragmentManager, FragmentPlaylistEditBottomSheet.TAG
            )
            return@setOnLongClickListener(true)
        }
    }
}