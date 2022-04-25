package com.example.forkieplayer.play

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.databinding.RecyclerPlayItemBinding
import java.util.*

class PlayAdapter(private val datas: MutableList<PlayData>, private val recyclerOnClick: IPlay): RecyclerView.Adapter<PlayAdapter.PlayViewHolder>() {
    lateinit var context: Context

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        context = parent.context
        return PlayViewHolder(RecyclerPlayItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        holder.bind(datas[position])

        // TODO: 리사이클러뷰 아이템 클릭시 영상 재생되도록 함
        holder.itemView.setOnClickListener {
            recyclerOnClick.triggerChangeVideo(datas[position].videoId, datas[position].start.toFloat())
            CustomToast.makeText(context, "seq : ${datas[position].sequence}, start : ${datas[position].start}, end : ${datas[position].end}")?.show()
        }

        // TODO: 리사이클러뷰 아이템 롱클릭시 순서 바뀌게 함
        holder.itemView.setOnLongClickListener {
            return@setOnLongClickListener(true)
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(datas, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    class PlayViewHolder(private val binding: RecyclerPlayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlayData) {
            binding.ivThumbnail.setImageResource(data.thumbnail)
            binding.tvTitle.text = data.title
            binding.tvChannelName.text = data.channelTitle
            binding.ivDelete.setOnClickListener {
                //TODO : 삭제 눌렀을 때
                Log.d("2minha", "delete click")
            }
            binding.ivEdit.setOnClickListener {
                //TODO : 수정 눌렀을 때
                Log.d("2minha", "edit click")
            }
        }
    }
}