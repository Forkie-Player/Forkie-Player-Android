package com.example.forkieplayer.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.RecyclerSearchHitItemBinding
import com.example.forkieplayer.video.VideoActivity

class SearchHitViewHolder(val binding: RecyclerSearchHitItemBinding): RecyclerView.ViewHolder(binding.root)

class SearchHitAdapter(val hitData: List<SearchHitData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return hitData.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return SearchHitViewHolder(RecyclerSearchHitItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SearchHitViewHolder).binding

        Glide.with(context)
            .load(hitData[position].thumbnail)
            .error(R.drawable.no_item)
            .into(binding.ivThumbnail)

        binding.tvTitle.text = hitData[position].title

        Glide.with(context)
            .load(hitData[position].channelImg)
            .error(R.drawable.no_item)
            .into(binding.ivProfile)

        binding.tvName.text = hitData[position].channelTitle

        holder.itemView.setOnClickListener {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("videoId", hitData[position].videoId)
            intent.putExtra("title", hitData[position].title)
            intent.putExtra("thumbnail", hitData[position].thumbnail)
            intent.putExtra("channelTitle", hitData[position].channelTitle)
            intent.putExtra("channelImg", hitData[position].channelImg)
            intent.putExtra("duration", fDuration(hitData[position].duration))
            context.startActivity(intent)
        }
    }

    fun fDuration(duration: String): Float {
        val colonCnt = duration.count { c -> c == ':'}
        val times = duration.split(":")
        var fDuration = 0.0f

        fDuration += if (colonCnt == 1) {
            ((times[0].toInt() * 60) + times[1].toInt())
        } else {
            ((times[0].toInt() * 3600) + (times[1].toInt() * 60) + times[2].toInt())
        }

        return fDuration
    }
}