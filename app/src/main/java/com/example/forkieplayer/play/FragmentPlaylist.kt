package com.example.forkieplayer.play

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistBinding

class FragmentPlaylist : Fragment() {

    lateinit var playActivity: PlayActivity
    lateinit var adapter: PlayAdapter

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        playActivity = context as PlayActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        // playlist bar 클릭시 비디오 정보 나오게 함
        binding.cvPlaylistBar.setOnClickListener(View.OnClickListener {
            (activity as PlayActivity).changeEditInfo()
        })

        // 리사이클러뷰 세팅
        val datas = mutableListOf<PlayData>()
        for (i in 1..10){
            datas.add(PlayData(R.drawable.play_thumbnail_temp, "[Playlist] 옷장 정리 싹 하면서 듣는 상쾌한 보사노바 🍐ㅣRefreshing bossa nova", "essentiall;"))
        }

        binding.recyclerPlay.layoutManager = LinearLayoutManager(playActivity)
        adapter = PlayAdapter(datas)
        binding.recyclerPlay.adapter = adapter

        return binding.root
    }
}