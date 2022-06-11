package com.example.forkieplayer.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.RecyclerSearchResultItemBinding
import com.example.forkieplayer.httpbody.SearchInfo
import com.example.forkieplayer.video.VideoActivity

class SearchResultViewHolder(val binding: RecyclerSearchResultItemBinding): RecyclerView.ViewHolder(binding.root)

class SearchResultAdapter(val resultData: List<SearchResultData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    // 항목의 개수 반환
    override fun getItemCount(): Int {
        return resultData.size
    }

    // ViewHolder를 준비하기 위해 호출, ViewHolder 객체를 생성해서 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return SearchResultViewHolder(RecyclerSearchResultItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // onCreateViewHolder에서 리턴된 ViewHolder 객체의 뷰 항목 데이터를 출력하거나 이벤트를 걸기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SearchResultViewHolder).binding

        Glide.with(context)
            .load(resultData[position].thumbnailImg)
            .error(R.drawable.no_item)
            .into(binding.ivThumbnail)

        binding.tvTitle.text = resultData[position].title

        Glide.with(context)
            .load(resultData[position].profileImg)
            .error(R.drawable.no_item)
            .into(binding.ivProfile)

        binding.tvName.text = resultData[position].name

        //TODO: 서버 연동 후 아이템 클릭시 아이템 아이디와 함께 넘기기
        holder.itemView.setOnClickListener {
            // TODO : searchResult에서 필요한 정보 뽑아다가 넘기기
            val intent = Intent(context, VideoActivity::class.java)
            context.startActivity(intent)
        }
    }
}