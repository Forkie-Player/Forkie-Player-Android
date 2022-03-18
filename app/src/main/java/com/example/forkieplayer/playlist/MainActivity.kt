package com.example.forkieplayer.playlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityMainBinding
import com.example.forkieplayer.profile.ProfileActivity
import com.example.forkieplayer.search.SearchActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PlaylistAdapter
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 리사이클러뷰 설정
        val datas = mutableListOf<PlaylistData>()
        datas.add(PlaylistData(R.drawable.ic_playlist_default, "default", 0))

        fragmentManager = this.supportFragmentManager

        binding.recyclerPlaylist.layoutManager = GridLayoutManager(this, 2)
        adapter = PlaylistAdapter(datas, fragmentManager)
        binding.recyclerPlaylist.adapter = adapter

        // 하단 추가 버튼 클릭시 플레이리스트 편집 fragment 뜨게 함
        binding.btnBottom.setOnClickListener {
            FragmentPlaylistAddBottomSheet.newInstance().show(
                fragmentManager, FragmentPlaylistAddBottomSheet.TAG
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId) {
            R.id.mypage -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                super.onOptionsItemSelected(item)
            }
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                super.onOptionsItemSelected(item)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}