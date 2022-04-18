package com.example.forkieplayer.play

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.FragmentPlaylistBinding

class FragmentPlaylist : Fragment() {

    lateinit var binding: FragmentPlaylistBinding
    lateinit var playActivity: PlayActivity
    lateinit var playAdapter: PlayAdapter

    // context 획득
    override fun onAttach(context: Context) {
        super.onAttach(context)
        playActivity = context as PlayActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        // playlist bar 클릭시 비디오 정보 나오게 함
        binding.cvPlaylistBar.setOnClickListener(View.OnClickListener {
            (activity as PlayActivity).changeEditInfo()
        })

        val datas = mutableListOf<PlayData>()
        val range = (0..3000)
        for (i in 1..10){
            datas.add(PlayData(i+15, "[Playlist] 행복은 포근한 강아지야🐶 | 기분이 뽀송-해지는 굿모닝 팝 ($i)", "xQcWOm7la0Y", i, R.drawable.video_thumbnail_temp,  range.random(), range.random()+100 ,R.drawable.channel_temp,"essentiall ($i);"))
        }
        playAdapter = PlayAdapter(playActivity, datas)

        binding.listview.apply {
            adapter = playAdapter
        }

        binding.listview.setOnItemClickListener { adapterView, view, i, l ->
            val selectedData: PlayData = adapterView.getItemAtPosition(i) as PlayData

            (activity as PlayActivity).changeVideo(selectedData.videoId, selectedData.start.toFloat())
            CustomToast.makeText(playActivity, "seq : ${selectedData.sequence}, start : ${selectedData.start}, end : ${selectedData.end}")?.show()
        }

        return binding.root
    }
}