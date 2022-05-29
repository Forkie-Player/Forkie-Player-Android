package com.example.forkieplayer.playlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forkieplayer.CustomToast
import com.example.forkieplayer.R
import com.example.forkieplayer.databinding.ActivityMainBinding
import com.example.forkieplayer.profile.ProfileActivity
import com.example.forkieplayer.search.SearchActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PlaylistAdapter
    lateinit var fragmentManager: FragmentManager
    lateinit var getPlaylistViewModel: GetPlaylistViewModel

    val datas = arrayListOf<PlaylistData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        getPlaylistViewModel = ViewModelProvider(this).get(GetPlaylistViewModel::class.java)
        subscribeViewModel()

        callGetPlaylistAPI()

        // 리사이클러뷰 설정
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

    private fun subscribeViewModel() {
        getPlaylistViewModel.okCode.observe(this){
            if(it) {
                val playlistData = getPlaylistViewModel.playlistDataList

                if (playlistData.isNullOrEmpty()) {
                    binding.apply {
                        recyclerPlaylist.visibility = View.GONE
                        groupEmpty.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        recyclerPlaylist.visibility = View.VISIBLE
                        groupEmpty.visibility = View.GONE
                    }

                    // TODO: 플레이리스트 갯수 받아오기
                    playlistData.forEach { i ->
                        if(i.thumbnail != null) {
                            datas.add(PlaylistData(i.thumbnail, i.title, 30))
                        } else {
                            // TODO: thumbnail null일 때 기본 이미지 설정하기
                            datas.add(PlaylistData("https://i.ytimg.com/vi/ioNng23DkIM/hq720.jpg?sqp=-oaymwEXCNAFEJQDSFryq4qpAwkIARUAAIhCGAE=&rs=AOn4CLDbvCoKyPCuxzHAlfdn0ft3gRTJWA", i.title, 30))
                        }
                    }
                }
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 조회 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun callGetPlaylistAPI() = getPlaylistViewModel.requestUserPlaylistInfo()

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