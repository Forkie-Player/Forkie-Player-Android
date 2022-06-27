package com.example.forkieplayer.video

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.RecyclerPlaylistItemBinding
import com.example.forkieplayer.httpbody.PlaylistInfo

class SelectPlaylistViewHolder(val binding: RecyclerPlaylistItemBinding): RecyclerView.ViewHolder(binding.root)

class SelectPlaylistAdapter(var datas: ArrayList<PlaylistInfo>, val fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context

    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return datas.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return SelectPlaylistViewHolder(RecyclerPlaylistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SelectPlaylistViewHolder).binding

        Glide.with(context)
            .load(datas[position].thumbnail)
            .error(R.drawable.play_temp)
            .into(binding.ivImage)

        binding.tvTitle.text = datas[position].title
        binding.tvCount.text = "12"

        holder.itemView.setOnClickListener {
            FragmentAddVideoDialog.newInstance(datas[position].id, datas[position].title).show(
                fragmentManager, FragmentSelectPlaylistBottomSheet.TAG
            )
        }
    }
}