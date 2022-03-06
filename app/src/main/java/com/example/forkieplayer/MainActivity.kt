package com.example.forkieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forkieplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<PlaylistData>()
        // TODO: 초기 화면에 3개 보이도록 임의로 세팅, 나중에 수정
        for (i in 1..3){
            datas.add(PlaylistData(R.drawable.playlist_sample, "강쥐귀여워강쥐짱짱", 57))
        }

        binding.recyclerPlaylist.layoutManager = GridLayoutManager(this, 2)
        adapter = PlaylistAdapter(datas)
        binding.recyclerPlaylist.adapter = adapter

        // TODO: 버튼 누르면 추가되도록 설정, 나중에 수정
        binding.btnBottom.setOnClickListener {
            datas.add(PlaylistData(R.drawable.playlist_sample, "강쥐귀여워강쥐짱짱", 57))
            adapter.notifyDataSetChanged()
        }
    }
}