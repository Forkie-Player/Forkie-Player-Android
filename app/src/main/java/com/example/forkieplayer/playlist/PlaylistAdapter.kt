package com.example.forkieplayer.playlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.RecyclerPlaylistItemBinding
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.play.PlayActivity

class PlaylistViewHolder(val binding: RecyclerPlaylistItemBinding): RecyclerView.ViewHolder(binding.root)

class PlaylistAdapter(var datas: ArrayList<PlaylistInfo>, val fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        Glide.with(context)
            .load(datas[position].thumbnail)
            .error("https://velog.velcdn.com/images/alsgk721/post/bb6d186b-5352-4db3-9a92-09d31cc81733/image.png")
            .into(binding.ivImage)

        binding.tvTitle.text = datas[position].title
        binding.tvCount.text = datas[position].playCount.toString()

        // 리사이클러뷰 아이템 롱클릭시 플레이리스트 편집 fragment 뜨게 함
        holder.itemView.setOnLongClickListener {
            FragmentPlaylistEditBottomSheet.newInstance(datas[position].id, position).show(
                fragmentManager, FragmentPlaylistEditBottomSheet.TAG
            )
            return@setOnLongClickListener(true)
        }

        holder.itemView.setOnClickListener {
            val id = datas[position].id
            val intent = Intent(context, PlayActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }

    fun addData(newData : PlaylistInfo){
        datas.add(newData)
        notifyDataSetChanged()
    }

    fun deleteData(position: Int) {
        datas.removeAt(position)
        notifyDataSetChanged()
    }

    fun changeData(changePosition: Int, changeTitle: String) {
        val newData = PlaylistInfo(datas[changePosition].id, datas[changePosition].thumbnail, changeTitle)
        datas[changePosition] = newData
        notifyDataSetChanged()
    }
}