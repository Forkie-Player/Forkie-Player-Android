package com.example.forkieplayer.play

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.RecyclerPlayItemBinding
import java.util.*

class PlayAdapter(private val datas: MutableList<PlayData>, private val recyclerOnClick: IPlay, private val fragmentManager: FragmentManager): RecyclerView.Adapter<PlayAdapter.PlayViewHolder>() {
    class PlayViewHolder(private val binding: RecyclerPlayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlayData) {
            binding.ivThumbnail.setImageResource(data.thumbnail)
            binding.tvTitle.text = data.title
            binding.tvChannelName.text = data.channelTitle
        }
    }

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

        holder.itemView.findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
            FragmentPlayDeleteDialog.newInstance().show(
                fragmentManager, FragmentPlayDeleteDialog.TAG
            )
            Toast.makeText(context, "삭제", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.findViewById<ImageView>(R.id.iv_edit).setOnClickListener {
            val intent = Intent(context, PlayEditActivity::class.java)
            context.startActivity(intent)
            Toast.makeText(context, "편집", Toast.LENGTH_SHORT).show()
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(datas, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}