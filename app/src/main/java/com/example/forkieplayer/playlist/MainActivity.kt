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
import com.example.forkieplayer.httpbody.CreatePlaylistRequest
import com.example.forkieplayer.httpbody.PlaylistInfo
import com.example.forkieplayer.profile.ProfileActivity
import com.example.forkieplayer.search.SearchActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PlaylistAdapter
    lateinit var fragmentManager: FragmentManager
    lateinit var playlistViewModel: PlaylistViewModel

    val datas = arrayListOf<PlaylistInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        playlistViewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        callGetPlaylistAPI()
        subscribeGetViewModel()
        subscribeAddViewModel()

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

    private fun subscribeGetViewModel() {
        playlistViewModel.userPlaylistokCode.observe(this){
            if(it) {
                val playlistData = playlistViewModel.playlistDataList

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
                            datas.add(i)
                        } else {
                            // TODO: thumbnail null일 때 기본 이미지 설정하기
                            datas.add(PlaylistInfo(i.id, "https://i.ytimg.com/vi/ioNng23DkIM/hq720.jpg?sqp=-oaymwEXCNAFEJQDSFryq4qpAwkIARUAAIhCGAE=&rs=AOn4CLDbvCoKyPCuxzHAlfdn0ft3gRTJWA", i.title))
                        }
                    }
                }
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 조회 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun subscribeAddViewModel() {
        playlistViewModel.addPlaylistOkCode.observe(this){
            if(it){
                // TODO: 새로운 플레이리스트 추가 됐을 때 기본 이미지 설정하기
                adapter.addData(PlaylistInfo(playlistViewModel.id, "https://i.ytimg.com/vi/ioNng23DkIM/hq720.jpg?sqp=-oaymwEXCNAFEJQDSFryq4qpAwkIARUAAIhCGAE=&rs=AOn4CLDbvCoKyPCuxzHAlfdn0ft3gRTJWA", playlistViewModel.title))
            } else {
                CustomToast.makeText(this, "죄송합니다. 플레이리스트 추가 요청에 실패하여 잠시후 다시 시도해주세요.")?.show()
            }
        }
    }

    private fun callGetPlaylistAPI() = playlistViewModel.requestUserPlaylistInfo()

    private fun callCreatePlaylistAPI(createPlaylistInfo: CreatePlaylistRequest) = playlistViewModel.requestCreatePlaylist(createPlaylistInfo)

    fun addPlaylist(title: String) {
        val createPlaylistInfo = CreatePlaylistRequest(title = title)
        callCreatePlaylistAPI(createPlaylistInfo)
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